package com.learning.userservice.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hotel_data")
public class Hotel {

	@Id
	@Column(name = "ID")
	private String hotelId;
	@Column(name = "HOTEL_NAME")
	private String hotelName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "CONTACT_NUMBER")
	private String contactNum;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "PINCODE")
	private String pincode;
	@Column(name = "CITY")
	private String city;
	@Column(name = "REVIEW")
	private List<String> review;
}
