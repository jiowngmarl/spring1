package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Bean // 비밀번호 암호화 : 단방향
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean // 메모리상 인증정보 등록 => 테스트 전용 방식
	InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = User.builder()
							   .username("user1")
							   .password(passwordEncoder().encode("1234"))
							   .roles("USER")
							   .build();
		
		UserDetails admin = User.builder()
								.username("admin1")
								.password(passwordEncoder().encode("1234"))
								.authorities("ROLE_ADMIN", "BOLE_USER")
								.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
}
