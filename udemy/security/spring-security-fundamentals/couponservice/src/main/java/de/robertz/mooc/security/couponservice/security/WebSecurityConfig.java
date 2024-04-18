package de.robertz.mooc.security.couponservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.formLogin(Customizer.withDefaults());

		http.authorizeHttpRequests(a ->

				a.requestMatchers(HttpMethod.GET,"/api/coupon/{code:^[A-Z]*$}",
								"/showGetCoupon", "/getCoupon", "/")
					.hasAnyRole("USER", "ADMIN")

				.requestMatchers(HttpMethod.GET,"/showCreateCoupon",
						"/createCoupon", "/createResponse")
						.hasAnyRole("ADMIN")

				.requestMatchers(HttpMethod.POST, "/api/coupon", "/saveCoupon")
					.hasRole("ADMIN")

				.requestMatchers(HttpMethod.POST, "/getCoupon", "/saveCoupon")
					.hasAnyRole("ADMIN", "USER")
		);

		http.csrf(AbstractHttpConfigurer::disable);

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
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
