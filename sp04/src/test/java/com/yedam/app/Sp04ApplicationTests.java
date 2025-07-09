package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.jpa.repository.CoustomerRepository;
import com.yedam.app.jpa.service.Customer;

@SpringBootTest
class Sp04ApplicationTests {
	
	@Autowired
	private CoustomerRepository custRepo;
	
	// 등록
	//@Test
	public void insert() {
		Customer cust = new Customer();
		cust.setName("홍길동");
		cust.setPhone("0102222");
		Customer result = custRepo.save(cust);
		System.out.println(result);
		assertEquals("홍길동", result.getName());
	}
	
	// 수정
	@Test
	public void update() {
		Optional<Customer> opcust = custRepo.findById(152L);
		if(opcust.isPresent()) {
			Customer cust = opcust.get();
			cust.setName("강호동");
			Customer result = custRepo.save(cust);
			System.out.println(result);
		}
	}
	
	//전체조회
	@Test
	public void selectAll() {
		List<Customer> list = custRepo.findAll();
		System.out.println(list);
	}
	
	//조건별 조회
	@Test
	public void findLike() {
		List<Customer> equalResult = custRepo.findByName("홍");
		System.out.println("equal : " + equalResult);
		List<Customer> likeResult = custRepo.findByNameContaining("홍");
		System.out.println("like : " + likeResult);
	}

}
