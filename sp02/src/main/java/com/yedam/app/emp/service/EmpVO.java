package com.yedam.app.emp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // 생성할려고 하는 클래스에게 넘겨줄려는 
@AllArgsConstructor
@NoArgsConstructor // @Builder 시 생략되므로 명시적으로 추가
public class EmpVO {
	
	private Integer employeeId;
	private String lastName;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;
	private String jobId;
	private double salary;
}
