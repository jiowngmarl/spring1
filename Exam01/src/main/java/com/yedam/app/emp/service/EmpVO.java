package com.yedam.app.emp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmpVO {
	private Integer bookNo;
	private String bookName;
	private String bookCoverImg;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bookDate;
	private Integer bookPrice;
	private String bookPublisher;
	private String bookInfo;
}
