package de.robertz.mooc.security.couponservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	/*
	* Spring will inject our UserDetailsServiceImpl.
	* */
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public boolean login(String userName, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
		authenticationManager.authenticate(token);
		boolean result = token.isAuthenticated();
		if (result) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		return result;
	}
}
