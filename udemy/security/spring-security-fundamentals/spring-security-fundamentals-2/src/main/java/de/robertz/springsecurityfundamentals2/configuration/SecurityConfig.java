package de.robertz.springsecurityfundamentals2.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    /*
     * We can use a @Bean like this or extend WebSecurityConfigurerAdapter and override configure
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        // In this commit, only /hello is authenticated access, /bye is not allowed at all.
        // That is effectively, anyRequest().denyAll()
        http.authorizeHttpRequests(a -> a.requestMatchers("/hello")
                .authenticated())
            // Note: Because of CSRF and ReST semantics, logout needs to be a POST!
            .logout(logout -> logout
                .permitAll()
                .logoutUrl("/logout") // Specifies the logout URL
                .logoutSuccessUrl("/login?logout") // Redirects to this URL after successful logout
                .invalidateHttpSession(true) // Invalidates the HTTP session
                .deleteCookies("JSESSIONID")); // Remove cookie

        // We will see "Filter handling Before", "Filter handling After" in console
        http.addFilterBefore(new CustomSecurityFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }

    // We added our own CustomAuthProvider
//    @Bean
//    public UserDetailsService pseudoInMemUD() {
//        // Create our custom in memory User Details Service
//        InMemoryUserDetailsManager imud = new InMemoryUserDetailsManager();
//
//        // Create a mock user
//        UserDetails ud = User.withUsername("tom").password(
//                // Spring basic/login form will use BCrypt, so we need to encode the pw as well
//                passwordEncoder().encode("king"))
//            .authorities("read") // Role of the user
//            .build();
//
//        imud.createUser(ud);
//
//        return imud;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
