package com.vernum.gamesService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
public class GamesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamesServiceApplication.class, args);
	}

}
