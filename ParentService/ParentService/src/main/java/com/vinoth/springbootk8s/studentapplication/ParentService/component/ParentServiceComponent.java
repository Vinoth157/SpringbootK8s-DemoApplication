package com.vinoth.springbootk8s.studentapplication.ParentService.component;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.vinoth.springbootk8s.studentapplication.ParentService.DTO.AddressDTO;
import com.vinoth.springbootk8s.studentapplication.ParentService.DTO.ParentDTO;
import com.vinoth.springbootk8s.studentapplication.ParentService.Entity.Parent;

@Component
public class ParentServiceComponent {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private ModelMapper modelMapper;

	public ParentDTO convertToParentDTO(Parent parent) {
		AddressDTO addressDTO = webClientBuilder.build().get()
				.uri("http://ADDRESS-SERVICE/address/find/"+parent.getAddressId()).retrieve().bodyToMono(AddressDTO.class).block();
		ParentDTO parentDTO = modelMapper.map(parent, ParentDTO.class);
		parentDTO.setAddress(addressDTO);
		return parentDTO;
	}
	
	public Parent convertToParent(ParentDTO parentDTO) {
		long addressID = webClientBuilder.build().post()
				.uri("http://ADDRESS-SERVICE/address/save").bodyValue(parentDTO.getAddress())
				.retrieve().bodyToMono(Long.class).block();
		Parent parent = modelMapper.map(parentDTO, Parent.class);
		parent.setAddressId(addressID);
		return parent;
	}
}
