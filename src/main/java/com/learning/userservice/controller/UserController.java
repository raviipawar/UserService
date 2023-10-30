package com.learning.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.userservice.entities.User;
import com.learning.userservice.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/add")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUSer(@PathVariable String userId) {
		User user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
}
