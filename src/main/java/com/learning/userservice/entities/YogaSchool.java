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
@Table(name = "yoga_school_data")
public class YogaSchool {

	@Id
	@Column(name = "ID")
	private String schoolId;
	@Column(name = "YOGA_SCHOOL_NAME")
	private String schoolName;
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
	@Column(name = "OWNER")
	private String ownerName;
	@Column(name = "REVIEW")
	private List<String> review;
}
