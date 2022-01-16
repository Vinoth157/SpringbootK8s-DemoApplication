package com.vinoth.springbootk8s.studentapplication.ParentService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class ParentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParentServiceApplication.class, args);
	}
}
