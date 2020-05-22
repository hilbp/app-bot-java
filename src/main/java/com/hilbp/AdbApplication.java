package com.hilbp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

import com.hilbp.web.config.YamlPropertySourceFactory;

@SpringBootApplication
@EnableFeignClients
@PropertySource(
		factory = YamlPropertySourceFactory.class, 
		value = "classpath:configScreen/2160x1080.yml",
		encoding = "utf-8")
public class AdbApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(AdbApplication.class, args);
	}
	
	
}
