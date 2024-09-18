package com.paymentchain.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer // This annotation is used to make your Spring Boot application acts as a Eureka Server
@SpringBootApplication
public class ErurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErurekaserverApplication.class, args);
	}

}
