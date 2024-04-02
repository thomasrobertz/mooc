package de.robertz.security.eazybank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EazyBankApplication {
	public static void main(String[] args) {

		/* By default, spring will (try to) secure all pages (paths, endpoints) in our application.
		 * This is due to
		 *  		SpringBootWebSecurityConfiguration::defaultSecurityFilterChain
		 *
		 * Take a look at the code:

			SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
				http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());

		 * SpringBootWebSecurityConfiguration is a typical point or stepping stone, to
		 * start with security in Spring App.
		 * See also the comment in the class:
		 *
		 * 		If the user specifies their own SecurityFilterChain bean, this will back-off completely
		 * 		and the users should specify all the bits that they want to configure as part of the custom
		 * 		security configuration.
		 *
		 * This means we need to create a bean of type SecurityFilterChain ourselves (like above),
		 * if we want to create our own security config.
		 *
		 * In this commit, if you check the pages you will have to login for each.
		 */

		SpringApplication.run(EazyBankApplication.class, args);
	}

}
