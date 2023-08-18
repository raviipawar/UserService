package com.learning.userservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.learning.userservice.entities.Hotel;
import com.learning.userservice.exceptions.ResourceNotFoundException;
import com.learning.userservice.repositories.HotelRepository;

import lombok.AllArgsConstructor;

@Service("hotelService")
@AllArgsConstructor
public class HotelServiceImpl {

	private HotelRepository hotelRepository;

	public Hotel saveHotel(Hotel hotel) {
		String randomHotelId= UUID.randomUUID().toString();
		hotel.setHotelId(randomHotelId);
		return hotelRepository.save(hotel);
	}

	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	public Hotel getHotel(String hotelId) {
		return hotelRepository.findById(hotelId).orElseThrow( () -> new ResourceNotFoundException("Hotel not found on server..!!" +hotelId));
	}
}
