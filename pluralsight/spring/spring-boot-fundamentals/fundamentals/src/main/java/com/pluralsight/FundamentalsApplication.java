package com.pluralsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.pluralsight.*")
public class FundamentalsApplication {
	public static void main(String[] args) {
		SpringApplication.run(FundamentalsApplication.class, args);
	}
}
