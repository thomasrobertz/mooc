package de.robertz.springsecurityfundamentals2.configuration;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthProvider implements AuthenticationProvider {
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		if (userName.equals("tom") && password.equals("king")) {
			return new UsernamePasswordAuthenticationToken(userName, password, List.of());
		}
		else {
			throw new BadCredentialsException("Invalid");
		}
	}

	/*
	 * Authentication Manager uses this to check if the auth type is supported by this provider.
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
