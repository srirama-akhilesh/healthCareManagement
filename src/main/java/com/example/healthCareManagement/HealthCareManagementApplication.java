package com.example.healthCareManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.healthCareManagement.dao")
public class HealthCareManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthCareManagementApplication.class, args);
	}

}
