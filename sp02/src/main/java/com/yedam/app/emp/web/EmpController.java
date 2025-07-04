package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.common.web.ParameterController;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@Controller 
// 컨트롤러는 쉽게말해서 route, DispatherServlet이 활용한 정보를 등록하는 Bean
// route : 사용자의 요청(endpoint)와 그에 대한 처리
// URI + METHOD => Service => Response(View or Data)

@RequiredArgsConstructor
public class EmpController {

    private final ParameterController parameterController;
	// 해당 컨트롤러에 제공하는 서비스 목록
	private final EmpService empService;
	
	// GET : 조회, 빈페이지, 데이터 조작(삭제)
	// POST : 데이터 조작 (등록, 수정, 삭제)
	
	// 전체조회 : GET
	@GetMapping("empList") // 1) URI와 메서드 설정
	public String empList(Model model) { // Model객체는 화면에 데이터를 전달하기 위해서 사용함
		
		// 2) 수행하는 기능
		List<EmpVO> list = empService.findAllList();
		// 2-1) View에 전달할 데이터 담기
		model.addAttribute("emps", list);
		
		// 3) 응답 형태 : View
		return "emp/list";
		// classpath:/template/  emp/list   .html
		// prefix                return     suffix
	}
	
	// 단건조회 : GET => QueryString
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.findInfoById(empVO);
		model.addAttribute("emp", findVO);
		return "emp/info";
	}
	
	// 등록 - 페이지 : GET
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}
	
	// 등록 - 처리 : POST => <form/>태그의 submit을 활용하는 경우가 매우 많다 / 인코딩 타입은 QueryString 타입
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.createInfo(empVO);
		String url = null;
		if(eid > -1) {
			// 정상적으로 등록
			url = "redirect:empInfo?employeeId=" + eid;
		} else {
			// 등록되지 않은 경우
			url = "redirect:empList";
		}
		return url;
	}
	
	// 수정 - 페이지 : GET => 단건조회
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.findInfoById(empVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
	}
		
	// 수정 - 처리 : POST + AJAX + JSON
	@PostMapping("empUpdate")
	@ResponseBody // AJAX, AJAX를 사용하면 model객체를 사용하지않는다, AJAX는 화면없이 돌아가지만 model객체는 화면을 만들어주는 것                           
	public Map<String, Object> empUpdateProcess(@RequestBody /*JSON*/ EmpVO empVO) {
		return empService.modifyInfo(empVO);
	}
	
	// 삭제 - 처리 : GET => QueryString 방식
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.removeInfo(employeeId);
		return "redirect:empList";
	}
}
