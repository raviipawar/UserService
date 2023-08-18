package com.learning.userservice.entities;

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
@Table(name = "user_data")
public class User {
	@Id
	@Column(name = "ID")
	private String userId;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "CONTACT_NUMBER")
	private String contactNum;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "PINCODE")
	private String pincode;
	@Column(name = "SEX")
	private String sex;
	@Column(name = "NATIONALITY")
	private String nationality;
	@Column(name = "ABOUT")
	private String about;
}
