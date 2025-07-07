package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication 
//아래 3개의 어노테이션을 포함하는것이 SpringBootApplication 어노테이션이다
@SpringBootConfiguration
@EnableAutoConfiguration // auto-config
@ComponentScan // spring

@MapperScan(basePackages = "com.yedam.app.**.mapper") //mybatis
public class Sp02Application {

	public static void main(String[] args) {
		SpringApplication.run(Sp02Application.class, args);
	}

}
