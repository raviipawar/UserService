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

import com.learning.userservice.entities.Hotel;
import com.learning.userservice.service.HotelServiceImpl;

@RestController
public class HotelController {

	@Autowired
	private HotelServiceImpl hotelServiceImpl;

	@PostMapping("/add")
	public ResponseEntity<Hotel> createUser(@RequestBody Hotel hotel) {
		Hotel hotel1 = hotelServiceImpl.saveHotel(hotel);
		return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
	}

	@GetMapping("/getHotel/{hotelID}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelID) {
		Hotel hotel = hotelServiceImpl.getHotel(hotelID);
		return ResponseEntity.ok(hotel);
	}

	@GetMapping("/hotel-service/all")
	public ResponseEntity<List<Hotel>> getAllUser() {
		List<Hotel> allUser = hotelServiceImpl.getAllHotels();
		return ResponseEntity.ok(allUser);
	}
}
