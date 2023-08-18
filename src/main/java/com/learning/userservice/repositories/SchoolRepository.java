package com.learning.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.userservice.entities.YogaSchool;

public interface SchoolRepository extends JpaRepository<YogaSchool, String> {

}
