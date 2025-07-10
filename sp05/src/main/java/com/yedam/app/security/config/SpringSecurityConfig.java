package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

//    private final SecurityFilterChain filterChain;
//
//    SpringSecurityConfig(SecurityFilterChain filterChain) {
//        this.filterChain = filterChain;
//    }
	@Bean // 비밀번호 암호화 : 단방향
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}
	
	//인증 및 인가 : Lambda DSL
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http //Security가 적용될 URI / 핵심
			.authorizeHttpRequests( authrize
					-> authrize
					  .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
					  .requestMatchers("/","/all").permitAll()
					  .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
					  .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
					  .anyRequest().authenticated())
			.formLogin(formLogin // 필수
					-> formLogin
					.usernameParameter("id")
					.defaultSuccessUrl("/all"))
			.logout(logout   // 필수
					-> logout
					.logoutSuccessUrl("/all")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID"));
		
		//http.csrf(csrf -> csrf.disable());
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web ->
				web
					.ignoring()
					.requestMatchers("/images/**", "/js/**", "/css/**");
	}
/*	
	@Bean //메모리상 인증정보 등록 => 테스트 전용 방식
	InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = User.builder()
							   .username("user1")
							   .password(passwordEncoder().encode("1234"))
							   .roles("USER")
							   .build();
		
		UserDetails admin = User.builder()
							    .username("admin1")
							    .password(passwordEncoder().encode("1234"))
							    .authorities("ROLE_ADMIN", "ROLE_USER")
							    .build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
*/
}
	

	

