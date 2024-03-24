package com.ktu.couriers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ktu.couriers.repositories")
public class CouriersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouriersApplication.class, args);
	}

}
