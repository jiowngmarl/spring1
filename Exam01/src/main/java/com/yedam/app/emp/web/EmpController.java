package com.yedam.app.emp.web;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmpController {
	
	private final EmpService empService;
	
	//전체조회
	@GetMapping("bookList")
	public String empList(Model model) {
		List<EmpVO> list = empService.findAll();
		model.addAttribute("emps", list);
		return "emp/empList";
	}
	
	//등록 페이지
	@GetMapping("bookInsert")
	public String bookInsertForm() {
		return "emp/empInsert";
	}
	
	//등록 처리
	@PostMapping("bookInsert")
	public String bookInsertProcess(EmpVO empVO) {
		empService.createInfo(empVO);
		return "redirect:bookList";
	}

}
