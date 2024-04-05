package de.robertz.security.eazybank.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // Just a standard configuration
public class SecurityConfig {

	// In this commit, we have customized the security filter chain.
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(rs ->
				rs
						// The paths where the user has to be authenticated
						.requestMatchers("/dashboard", "/transcations").authenticated()

						// The paths we want to be publicly accessible
						.requestMatchers("/contact").permitAll()
		)
		// Users can log in with basic form as before
		.formLogin(withDefaults())	// Without this, user will get a browser popup login
		.httpBasic(withDefaults())
		.logout(logout -> logout.permitAll()
				.logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK)));

		// Side note: If we were to use anyRequest().denyAll() on all routes above, we would have to login
		// first and then get a 403 error on all pages. Then why the need to login first?
		// Because there's Authentication first and then Authorization!
		// So in this case we would actually be authenticated but not authorized.
		// Note that 403 is *authorization* error!
		// But what are use cases for denyAll? Security Testing, not allowing routes in dev but on prod (perhaps with help of profiles),...

		return http.build();
	}
}
