package com.vinoth.springbootk8s.studentapplication.AddressService.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
