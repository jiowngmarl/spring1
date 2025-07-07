package com.yedam.app.emp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder //빌터 패턴
@AllArgsConstructor
@NoArgsConstructor //@builder 시 생략되므로 명시적으로 추가
public class EmpVO {
	//map-underscore-to-camel-case 로 인해서 
	// employee_id -> employeeId -> setEmployeeId
	// 위 세팅이 없으면 employee_id -> setEmployee_id
	private Integer employeeId;
	private String lastName;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date hireDate;
	private String jobId;
	private Double salary;
	
	
	
}
