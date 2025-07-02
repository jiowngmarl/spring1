package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
class Sp02ApplicationTests {
	
	@Autowired // 필드 주입 : 테스트용, private로 지정된걸 강제로 필드주입 하기때문에 사용을 권장하지 않는다
	private EmpMapper empMapper;
	
	//@Test // 해당 메서드를 테스트하겠다고 선언
	public void selectAll() {
		List<EmpVO> list = empMapper.selectAll();
		for(EmpVO emp : list) {
			System.out.println(emp.getLastName());
		}
		assertTrue(!list.isEmpty()); //단정 메서드, junit이 제공하는 메서드, 
	}
	
	@Test
	public void selectOne() {
		EmpVO emp = EmpVO.builder().employeeId(100).build(); // Builder 역할 : instance 생성, field init 초기화 
		
		EmpVO findVO = empMapper.selectInfo(emp);
		System.out.println(findVO);
		
		assertEquals("King", findVO.getLastName()); // "내가 원하는값", 실행했을때 나오는 비교값 -> 같을때 성공을 의미
	}
	
	//@Test
	public void insertValue() {
		EmpVO emp = EmpVO.builder().employeeId(1000).lastName("Kang").email("kg@google.com").jobId("SA_REP").build();
		
		int result = empMapper.insertInfo(emp);
		assertEquals(1, result);
	}
	
	//@Test
	public void insertSelectKey() {
		EmpVO emp = EmpVO.builder().lastName("Hong").email("hong@naver.com").jobId("IT_PROG").salary(1200).build();
		int result = empMapper.insertInfo(emp);
		System.out.println("사원번호 : " + emp.getEmployeeId());
		
		assertEquals(1, result);
	}
	
	@Test
	public void updateInfo() {
		// 1) 단건조회
		EmpVO emp = EmpVO.builder()
						 .employeeId(1000)
						 .build();
		EmpVO findVO = empMapper.selectInfo(emp);

		// 2) 값 변경
		findVO.setSalary(2550);

		// 3) 테이블에 업데이트
		int result = empMapper.updateInfo(1000, findVO);
		
		assertEquals(1, result);
	}

}
