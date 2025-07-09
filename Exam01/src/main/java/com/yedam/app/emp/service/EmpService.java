package com.yedam.app.emp.service;

import java.util.List;

public interface EmpService {
	
	//전체조회
	public List<EmpVO> findAll();
	
	//단건조회
	
	//등록
	public int createInfo(EmpVO empVO);
	
	//수정
}

