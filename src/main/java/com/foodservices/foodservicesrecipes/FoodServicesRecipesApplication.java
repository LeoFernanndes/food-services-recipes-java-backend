package com.foodservices.foodservicesrecipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodServicesRecipesApplication {
	// TODO: check error related to missing SPRING_AMQP_DESERIALIZATION_TRUST_ALL environment variable
	public static void main(String[] args) {
		SpringApplication.run(FoodServicesRecipesApplication.class, args);
	}
}