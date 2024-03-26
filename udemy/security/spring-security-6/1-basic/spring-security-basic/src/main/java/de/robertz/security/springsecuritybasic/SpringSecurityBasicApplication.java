package de.robertz.security.springsecuritybasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityBasicApplication {

	/*
	* Spring will secure access to our Endpoint.
	* It will generate a one-time Password in the console.
	*
	* Go to http://localhost:8080/welcome
	* Login: 	name: user
	* 				password: the above generated
	*
	* Note that once logged in, we can refresh the page and will not be asked for credentials again.
	* If you look at the request you'll see why: there is a cookie:
	* 	JSESSIONID=87DFE169E154CC68013AA345646BD88E
	*
	* This is all made possible simply by adding the spring security dependency
	* 			<groupId>org.springframework.boot</groupId>
 	* 			<artifactId>spring-boot-starter-security</artifactId>
 	*
 	* In this commit, we have given an example of how to set static username and password in application.properties.
 	* Once the above cookie is deleted, we can login with the credentials from application.properties.
 	* Again all is handled by spring security.
 	* */
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicApplication.class, args);
	}

}
