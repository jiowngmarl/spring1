package com.yedam.app.emp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmpReqVO {

	private String jobId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date sdt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date edt;
	
}
