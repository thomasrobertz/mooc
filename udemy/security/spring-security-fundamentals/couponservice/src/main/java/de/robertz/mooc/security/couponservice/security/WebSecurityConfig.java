package de.robertz.mooc.security.couponservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.httpBasic(Customizer.withDefaults());

		http.authorizeHttpRequests(a ->

				a.requestMatchers(HttpMethod.GET, "/api/coupon/**")

					// Compare to SQL. "ROLE_" is automatically prepended by spring security.
					.hasAnyRole("USER", "ADMIN")

				.requestMatchers(HttpMethod.POST, "/api/coupon")
					.hasRole("ADMIN"));

		http.csrf(AbstractHttpConfigurer::disable);

		return http.build();
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
