package com.vinoth.springbootk8s.studentapplication.ParentService.ThreadsTask;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import com.vinoth.springbootk8s.studentapplication.ParentService.DTO.AddressDTO;

public class AddressDTOTask implements Callable<AddressDTO> {

	@Autowired
	private WebClient.Builder webClientBuilder;
	
	private long addressId;
		
	public AddressDTOTask(long addressId) {
		super();
		this.addressId = addressId;
	}


	@Override
	public AddressDTO call() throws Exception {
		AddressDTO addressDTO = webClientBuilder.build().get()
				.uri("http://ADDRESS-SERVICE/address/find/"+addressId).retrieve().bodyToMono(AddressDTO.class).block();
		return addressDTO;
	}

}
