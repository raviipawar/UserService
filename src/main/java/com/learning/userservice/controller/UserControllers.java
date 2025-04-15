package com.learning.userservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.userservice.entities.User;
import com.learning.userservice.service.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserControllers {
	
	
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@GetMapping("/")
	public String printHello() {
		return "Hello";
	}
	
	@GetMapping("/all")
	public List<User> getAllUsers(){
		System.out.println("inside fetch all");
		return userDetailsServiceImpl.fetchAllUsers();
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable Long id){
		System.out.println("inside fetch all");
		return userDetailsServiceImpl.fetchUserById(id);
	}
}
