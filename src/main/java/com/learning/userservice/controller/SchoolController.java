package com.learning.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.userservice.entities.YogaSchool;
import com.learning.userservice.service.SchoolServiceImpl;

@RestController
public class SchoolController {

	@Autowired
	private SchoolServiceImpl schoolServiceImpl;

	@PostMapping("/addSchool")
	public ResponseEntity<YogaSchool> createUser(@RequestBody YogaSchool yogaSchool) {
		YogaSchool school = schoolServiceImpl.saveSchool(yogaSchool);
		return ResponseEntity.status(HttpStatus.CREATED).body(school);
	}

	@GetMapping("getSchool/{schoolId}")
	public ResponseEntity<YogaSchool> getSingleUSer(@PathVariable String schoolId) {
		YogaSchool yogaSchool = schoolServiceImpl.getSchool(schoolId);
		return ResponseEntity.ok(yogaSchool);
	}

	@GetMapping("/yogaSchool-service/all")
	public ResponseEntity<List<YogaSchool>> getAllUser() {
		List<YogaSchool> allUser = schoolServiceImpl.getAllSchools();
		return ResponseEntity.ok(allUser);
	}
}
