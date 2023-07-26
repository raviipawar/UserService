package com.learning.userservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.learning.userservice.entities.User;
import com.learning.userservice.exceptions.ResourceNotFoundException;
import com.learning.userservice.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service("userService")
@AllArgsConstructor
public class UserServiceImpl {

	private UserRepository userRepository;

	public User saveUser(User user) {
		String randomUserId= UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User getUser(String userId) {
		return userRepository.findById(userId).orElseThrow( () -> new ResourceNotFoundException("User not found on server..!!" +userId));
	}
}
