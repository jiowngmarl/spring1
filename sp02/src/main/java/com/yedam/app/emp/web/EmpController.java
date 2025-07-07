package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@Controller 
// DispatherServlet이 활용한 정보를 등록하는 Bean
// route : 사용자의 요청(endpoint)와 그에 대한 처리
// 	URI + HTTP METHOD (get post delete) => Service => Response(View or Data)

@RequiredArgsConstructor
public class EmpController {
	// 해당 컨트롤러에서 제공하는 서비스 목록
	//final이 들어가면 무조건 생성자 요청, RequiredArgsConstructor(lombok) 사용.
	private final EmpService empService;
	
	// GET : 조회, 빈페이지, 데이터 조작(삭제)
	// POST : 데이터 조작(등록, 수정)
	
	//전체조회 : GET
	@GetMapping("empList")// URI + METHOD
	public String empList(Model model) {
		//2) 수행기능 => Service
		List<EmpVO> list = empService.findAllList();
		//2-1) view 에 전달할 데이터 담기
		model.addAttribute("emps", list);
		//3) 응답형태 : View
		return "emp/list";
		//classpath:/template/emp/list.html
		//prefix              return   suffix
	}
	//단건조회 : GET => QueryString | empInfo?employeeId(key)=100(value)
	@GetMapping("empInfo")
	public String empInfo(EmpVO empVO, Model model) {
		EmpVO findVO = empService.findInfoById(empVO);
		model.addAttribute("emp", findVO);
		return "emp/info";
		//classpath:/template/emp/info.html
		
	}
	
	
	// 등록 - 페이지 : GET
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
		//classpath:/template/emp/insert.html
	}
	
	// 등록 - 처리 : POST => <form/> submit QueryString
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.createInfo(empVO);
		String url = null;
		if(eid > -1 ) {
			//정상적으로 등록
			url = "redirect:empInfo?employeeId=" + eid;
		}else {
			//등록되지 않은 경우 -> 얘는 등록이 실패했을때 실질적으로 실행되지 않을것 - 등록 실패 = 에러 이기 때문에 코드가 중단될것임.
			url="redirect:empList";
		}
		return url;
	}
	
	// 수정 - 페이지 : GET <=> 단건조회와 같음. 경로, 이름 빼고 나머지는 다 같다고 보면 됌
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.findInfoById(empVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
	}
	
	// 수정 - 처리 : POST + AJAX + JSON
	@PostMapping("empUpdate")  
	@ResponseBody // AJAX => Model 사용하지 않음
												//JSON / 보내주는 데이터가 JSON 만이 아닐수도 있음.
	public Map<String, Object> empUpdateProcess(@RequestBody EmpVO empVO) {
		return empService.modifyInfo(empVO);
	}
	
	// 삭제 - 처리 : GET => QueryString : empDelete?employeeId=value
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) { 
		empService.removeInfo(employeeId);
		return "redirect:empList"; //삭제된 데이터는 안보이게 돌려준다
	}

	
}
