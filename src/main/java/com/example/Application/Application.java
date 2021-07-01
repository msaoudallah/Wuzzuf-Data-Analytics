package com.example.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import backend.Apis;

@SpringBootApplication
@ComponentScan(basePackageClasses = Apis.class)

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
		@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
}


