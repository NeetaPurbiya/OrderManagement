package com.orderms.orderitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderitemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderitemApplication.class, args);
	}

}
