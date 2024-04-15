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

				// In the previous version we allowed any string to be passed as coupon name ("/api/coupon/**")
				// Here we restrict that by RegEx, which is bound to the 'coupon' PathVariable.
				a.requestMatchers(HttpMethod.GET, "/api/coupon/{code:^[A-Z]*$}")

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
