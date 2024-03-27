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
 	*
 	* If we look at the code for AuthorizationFilter, we can see the doFilter() method which accepts FilterChain and calls chain elements (if any).
 	* With our basic auth outlined above, we can see how the html login page is generated in DefaultLoginPageGeneratingFilter.
 	* DefaultLoginPageGeneratingFilter being a chain element itself, also accepts FilterChain and calls further chain elements.
 	* One of these could be UsernamePasswordAuthenticationFilter, which is concerned with obtaining and matching the password.
 	* This class returns an Authentication we see in the internal security flow.
 	* Next we can look at ProviderManager, which implements AuthenticationManager.
 	* We can see here how it iterates all authentication providers (first successful auth wins):
 	* 			for (AuthenticationProvider provider : getProviders()) { ...
 	*
 	* One such provider is AbstractUserDetailsAuthenticationProvider, where we can see the actual auth logic implementation:
 	* 			public Authentication authenticate( ...
 	*
 	* DaoAuthenticationProvider is an implementor of AbstractUserDetailsAuthenticationProvider, we can see here how it helps in getting UserDetails:
 	*				protected final UserDetails retrieveUser(String username, ...
 	*
 	* UserDetails itself then helps in getting the user details, see f.i. InMemoryUserDetailsManager.
 	* This is also what's being used in the above example.
 	*
 	* Activity: Set breakpoints and debug:
 	* 	AuthorizationFilter::doFilter
 	* 	DefaultLoginPageGeneratingFilter::generateLoginPageHtml
 	* 		-> Login
 	* 	UsernamePasswordAuthenticationFilter::attemptAuthentication
 	* 	ProviderManager::authenticate
 	* 	AbstractUserDetailsAuthenticationProvider::authenticate
 	* 	InMemoryUserDetailsManager::loadUserByUserName
 	*
 	* By this point if we continue an authentication should be passed back to AuthenticationManager (ProviderManager)
 	* and the web page should load.
 	*
 	* Again, this all can be related back to the internal spring security flow
 	* Picture from https://www.javainuse.com/webseries/spring-security-jwt/chap3
 	* */
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicApplication.class, args);
	}

}
