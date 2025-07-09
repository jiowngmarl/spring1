package com.yedam.app.emp.mapper;

import java.util.List;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {

	// 전체 조회
	public List<EmpVO> selectAll();
	
	// 단건조회
	
	// 등록
	public int insertInfo(EmpVO empVO);
}
