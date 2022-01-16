package com.vinoth.springbootk8s.studentapplication.AddressService.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

	private String street;
	private String city;
	private String state;
	private String country;
}
