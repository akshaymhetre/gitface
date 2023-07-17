package com.akshay.gitface.manageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class ManageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageServiceApplication.class, args);
	}

}
