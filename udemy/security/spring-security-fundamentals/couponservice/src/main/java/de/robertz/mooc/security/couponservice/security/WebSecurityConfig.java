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

		// We don't need this anymore now, because we have implemented our own login form.
		// http.formLogin(Customizer.withDefaults());

		http.authorizeHttpRequests(a -> a

				// Anyone should be able to log in and register at any time
				.requestMatchers("/login", "/showRegistration", "/registerUser", "/").permitAll()

				.requestMatchers(HttpMethod.GET,"/api/coupon/{code:^[A-Z]*$}",
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

		http.securityContext(c -> c.requireExplicitSave(true));
		http.csrf(AbstractHttpConfigurer::disable);

		// Standard logout is already handled by Spring Security.
		// But we want to make sure session cookies are deleted and configure the logout page:
		http.logout(l -> l
				.invalidateHttpSession(true) // Is the default, just shown for demonstration purposes
				.logoutSuccessUrl("/"));

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
