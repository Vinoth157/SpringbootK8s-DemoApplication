package com.vinoth.springbootk8s.studentapplication.ParentService.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ParentDTO {

	private String firstName;
	private String lastName;
	private String gender;
	private AddressDTO address;
}
