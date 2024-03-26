package de.robertz.security.springsecuritybasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityBasicApplication {

	/*
	* Spring will generate a one-time Password in the console.
	* Go to http://localhost:8080/welcome
	* Login: 	name: user
	* 				password: the above generated
	* */

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicApplication.class, args);
	}

}
