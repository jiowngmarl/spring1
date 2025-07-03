package com.yedam.app.common.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@CrossOrigin(origins = "*") // cors 설정
@Controller
public class ParameterController {
	
	// QueryString(질의문자열) : key=value&key=value&...
	// Content-type : application/x-www-form-urlencode
	// Method : 상관없음
	
	// QueryString, 1) 커맨드객체 (어노테이션을 사용하지 않는 방식)
	@RequestMapping("comobj")
	@ResponseBody // AJAX 
	public String commandQbject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
	}
	
	// QueryString, 2) @RequestParam (기본값) , 여기서 기본값은 Wrap class를 뜻함
	@RequestMapping("reqparm")
	@ResponseBody
	public String requestParam(@RequestParam Integer employeeId, String lastName) {
		String result = "";
		result += "Path : /reqparm \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		return result;		
	}
	
	// @PathVariable : 경로에 값을 포함하는 방식, 단일 값
	// Method는 상관없음
	// Content-type도 상관없음
	@RequestMapping("path/{id}")
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		String result = "";
		result += "Path : /path/{id} \n";
		result += "\t id : " + id;
		return result;		
	}	
	
	// @RequestBody : JSON 포맷, 배열 or 객체
	// Method : POST, PUT
	// Content-type : application/json
	@RequestMapping("resbody")
	@ResponseBody
	public EmpVO requestBody(@RequestBody EmpVO empVO) {
		return empVO;		
	}	
	
	@RequestMapping("resbodyList")
	@ResponseBody
	public List<EmpVO> resbodyList(@RequestBody List<EmpVO> list) {
		return list;		
	}	
	
	// json으로 데이터를 보낼때는 배열이면 배열, 객체면 객체로 보내야 에러가 발생하지 않는다
	// JSON parse error: Cannot deserialize value of type -> 역직렬화 (문자열 -> 객체) 에 관한 에러 
}
