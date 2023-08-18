package com.learning.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.userservice.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String> {

}
