package com.yedam.app.jpa.service;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 20, nullable = false)
	private String name;
	@Column(length = 20, nullable = false, unique = true)
	private String phone;
	private String email;
	@CreationTimestamp
	private Timestamp rdt;
	@UpdateTimestamp
	private Timestamp udt;
	
}
