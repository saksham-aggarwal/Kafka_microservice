package com.cwctravel.microservice.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

// This is main function of the Micro-service which starts an instance of KafkaMicroserviceApplication.
// This class is pre-created when we use Spring Initializr to generate a SpringBoot Application.

@SpringBootApplication
@EnableSwagger2
public class KafkaMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMicroserviceApplication.class, args);
	}

}
