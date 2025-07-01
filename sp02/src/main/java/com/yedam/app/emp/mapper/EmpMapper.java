package com.yedam.app.emp.mapper;

import java.util.List;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	// sql문을 연결할 Mapper 골격
	// 전체조회
	public List<EmpVO> selectAll();
	
	// 단건조회
	public EmpVO selectInfo(EmpVO empVO);
	
	// 등록
	public int insertInfo(EmpVO empVO);
}
