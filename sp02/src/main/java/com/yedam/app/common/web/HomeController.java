package com.yedam.app.common.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/*
	컨트롤러는 라우트를 의미 
	route = Endpoing(URL + Http Method)
	+ Service
	+ 응답형태 (View or Data)
*/
@Controller
public class HomeController {
	@RequestMapping("/") // 경로를 매핑하는 어노테이션
	public String mainPage(String message, Model model) { // Model = Request + Response 합친 느낌
		
		model.addAttribute("msg", message);
		
		return "home";
		// classpath:/templates/home.html
	}
	
	@GetMapping("/")
	public String mainPage() {
		return "home";
	}
	@PostMapping("/")
	public String mainStringMsgPage(String message, Model model) {
		model.addAttribute("msg", message);
		return "home";
	}
}
