package com.hilbp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdbApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(AdbApplication.class, args);
	}
	
	
}
