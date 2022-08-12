package com.shogood.checkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CheckoutMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckoutMicroserviceApplication.class, args);
	}
}
