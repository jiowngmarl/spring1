package com.yedam.app.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
  	route = EndPoint(URL + Http Method)
			+ Service
			+ 응답형태 (view or Data)
*/
@Controller 
public class HomeController {
	@RequestMapping("/")
	//경로를 설정하는 어노테이션                  // Model = Request + Response
	public String mainPage(String message, Model model) {
						// 리퀘스트 안에 담긴 데이터를 매개변수에 담는다. 
		// service 실행
		model.addAttribute("msg", message);
		return "home";
		// classpath:/templates/home.html  
	}
	
	@GetMapping("/")
	public String mainPage() {
		return "home";
	}
	@PostMapping("/")
	public String mainMsgPage(String message, Model model) {
		model.addAttribute("msg", message);
		return "home";
	}
	
}
