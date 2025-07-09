package com.yedam.app.jpa.service;

import java.security.Timestamp;
import java.sql.Time;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "TBL_USER") // JPA 에 사용하는 Entity 선언, name을 생략하면 클래스명이 테이블명이 됌
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	private Long no;
	@Column(name = "username", length = 20, nullable = false, unique = true)
	private String id;
	@Column(length = 20, nullable = false)
	private String password;
	private Integer age;
	
	@CreationTimestamp
	private Timestamp joinDate;
}
