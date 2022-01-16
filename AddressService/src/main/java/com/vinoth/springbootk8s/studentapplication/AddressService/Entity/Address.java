package com.vinoth.springbootk8s.studentapplication.AddressService.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private long addressId;
	private String street;
	private String city;
	private String state;
	private String country;
}
