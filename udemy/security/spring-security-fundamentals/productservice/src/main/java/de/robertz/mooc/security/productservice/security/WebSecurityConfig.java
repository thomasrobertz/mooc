package de.robertz.mooc.security.productservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class WebSecurityConfig {

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic(Customizer.withDefaults());

		http.authorizeHttpRequests(a -> a

						.requestMatchers("/login", "/showRegistration", "/registerUser", "/").permitAll()

						// UI
						.requestMatchers(HttpMethod.GET,"/showGetProduct")
						.hasAnyRole("USER", "ADMIN")

						.requestMatchers(HttpMethod.GET,"/showCreateProduct",
								"/createProduct", "/createResponse")
						.hasAnyRole("ADMIN")

						.requestMatchers(HttpMethod.POST,"/getProduct")
						.hasAnyRole("USER", "ADMIN")

						// API
						.requestMatchers(HttpMethod.GET, "/api/product/*").hasAnyRole("USER", "ADMIN")
						.requestMatchers(HttpMethod.POST, "/api/product").hasRole("ADMIN")
				);

		http.securityContext(c -> c.requireExplicitSave(true));
		http.csrf(AbstractHttpConfigurer::disable);

		http.logout(l -> l.logoutSuccessUrl("/"));

		return http.build();
	}

	@Bean
	AuthenticationManager authManager() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(provider);
	}

	@Bean
	SecurityContextRepository securityContextRepository() {
		// Save the security context across sessions (in HttpSession).
		return new DelegatingSecurityContextRepository(
				new RequestAttributeSecurityContextRepository(),
				new HttpSessionSecurityContextRepository());
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
