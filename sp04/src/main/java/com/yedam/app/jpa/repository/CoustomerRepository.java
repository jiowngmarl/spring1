package com.yedam.app.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yedam.app.jpa.service.Customer;

import jakarta.transaction.Transactional;

@Repository // Persistence 영역을 제어하는 Bean
public interface CoustomerRepository extends JpaRepository<Customer, Long> {
	
	// findAll, findById, save, deleteById 제외 별도 추가
	// SELECT * FROM customer WHERE name = ?1
	public List<Customer> findByName(String name); 
	// SELECT * FROM customer WHERE name = ?1 AND phone = ?2
	public List<Customer> findByNameAndPhone(String name, String phone);
	// SELECT * FROM customer WHERE name LIKE '%'?1'%'
	public List<Customer> findByNameContaining(String keyword);
	
	// JPQL
	// 1) SELECT
	@Query("SELECT u FROM Customer u WHERE u.name LIKE ?1ORDER BY u.id DESC") // 실제 테이블과 컬럼 명을 사용하는 것이 아닌 Entity 즉 클래스명과 필드명을 사용함
	public List<Customer> findByAndSort(String name);
	// 2) DML
	@Transactional
	@Modifying
	@Query("UPDATE Customer c SET c.name = ?1WHERE c.id = ?2")
	public int setFixedNameFor(String name, Long id);
}
