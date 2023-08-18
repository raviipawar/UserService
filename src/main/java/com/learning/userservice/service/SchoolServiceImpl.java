package com.learning.userservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.learning.userservice.entities.YogaSchool;
import com.learning.userservice.exceptions.ResourceNotFoundException;
import com.learning.userservice.repositories.SchoolRepository;

import lombok.AllArgsConstructor;

@Service("schoolService")
@AllArgsConstructor
public class SchoolServiceImpl {

	private SchoolRepository schoolRepository;

	public YogaSchool saveSchool(YogaSchool yogaSchool) {
		String randomschoolId= UUID.randomUUID().toString();
		yogaSchool.setSchoolId(randomschoolId);
		return schoolRepository.save(yogaSchool);
	}

	public List<YogaSchool> getAllSchools() {
		return schoolRepository.findAll();
	}

	public YogaSchool getSchool(String schoolId) {
		return schoolRepository.findById(schoolId).orElseThrow( () -> new ResourceNotFoundException("YogaSchool not found on server..!!" +schoolId));
	}
}
