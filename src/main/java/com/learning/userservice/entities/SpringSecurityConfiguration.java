package com.learning.userservice.entities;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Add steps into filter chain
		// 1) All steps are authenticated
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

		// if a request is not authenticated a web page is shown
		http.httpBasic(withDefaults());

		// CSRF ->post, put
		http.csrf().disable();
		return http.build();
	}
}
