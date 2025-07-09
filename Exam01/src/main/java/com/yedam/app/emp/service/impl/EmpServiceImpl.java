package com.yedam.app.emp.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {
	
	private final EmpMapper empMapper;
	@Override
	public List<EmpVO> findAll() {
		return empMapper.selectAll();
	}

	@Override
	public int createInfo(EmpVO empVO) {
		int result = empMapper.insertInfo(empVO);
		return result == 1 ? empVO.getBookNo() : -1;
	}
	

}
