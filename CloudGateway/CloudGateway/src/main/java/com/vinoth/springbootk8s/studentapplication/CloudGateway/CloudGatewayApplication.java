package com.vinoth.springbootk8s.studentapplication.CloudGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayApplication.class, args);
	}

//	@Bean
//	public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer(){
//		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(5)).build()).build());		
//	}
}
