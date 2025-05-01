package com.learning.userservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.userservice.entities.User;
import com.learning.userservice.jwt.JwtUtils;
import com.learning.userservice.service.UserDetailsServiceImpl;

@CrossOrigin(origins = {"http://localhost:8081", "http://192.168.1.6:8081"})
@RestController
@RequestMapping("/users")
public class UserControllers {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtUtils jwtUtils;

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id,
			@RequestHeader(value = "Authorization", required = false) String authHeader) {

		// Check for Authorization header
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
		}
		String token = authHeader.substring(7);
		if (!jwtUtils.validateJwtToken(token)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired JWT token");
		}
		String username = jwtUtils.getUserNameFromJwtToken(token);
		System.out.println("Authenticated as: " + username);
		Optional<User> user = userDetailsServiceImpl.fetchUserById(id);
		return user.isPresent() ? ResponseEntity.ok(user.get())
				: ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	}
}
