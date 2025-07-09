package com.yedam.app.board.service;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {

	private Integer bno;
	private String title;
	private String writer;
	private String contents;
	private Date regdate;
	private Date updatedate;
	private String image;
	
}
