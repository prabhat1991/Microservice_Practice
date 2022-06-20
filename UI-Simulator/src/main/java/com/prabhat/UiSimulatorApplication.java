package com.prabhat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UiSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiSimulatorApplication.class, args);
	}

}
