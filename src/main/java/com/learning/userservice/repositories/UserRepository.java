package com.learning.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.userservice.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
