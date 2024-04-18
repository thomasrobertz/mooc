package de.robertz.mooc.security.couponservice.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;
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

	@Autowired
	SecurityContextRepository securityContextRepository;

	@Override
	public boolean login(String userName, String password, HttpServletRequest request, HttpServletResponse response) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(token);
		boolean result = token.isAuthenticated();

		if (result) {

			// Get context
			SecurityContext context = SecurityContextHolder.getContext();

			// Set authentication
			context.setAuthentication(token);

			// Store in session
			securityContextRepository.saveContext(context, request, response);
		}

		return result;
	}
}
