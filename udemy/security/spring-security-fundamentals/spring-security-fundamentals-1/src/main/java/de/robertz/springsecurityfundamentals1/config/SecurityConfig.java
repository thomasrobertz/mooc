package de.robertz.springsecurityfundamentals1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService pseudoInMemUD() {
        // Create our custom in memory User Details Service
        InMemoryUserDetailsManager imud = new InMemoryUserDetailsManager();

        // Create a mock user
        UserDetails ud = User.withUsername("tom").password(
                    // Spring basic/login form will use BCrypt so we need to encode the pw as well
                    passwordEncoder().encode("king"))
                .authorities("read") // Role of the user
                .build();

        imud.createUser(ud);

        return imud;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(a -> a.anyRequest().authenticated());
        return http.build();
    }

}
