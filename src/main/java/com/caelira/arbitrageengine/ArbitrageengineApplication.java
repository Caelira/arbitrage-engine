package com.caelira.arbitrageengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArbitrageengineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArbitrageengineApplication.class, args);
	}

}
