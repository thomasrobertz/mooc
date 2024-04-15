package de.robertz.mooc.security.couponservice.security;

import java.util.Optional;

import de.robertz.mooc.security.couponservice.model.User;
import de.robertz.mooc.security.couponservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);

		// User expects Collection<? extends GrantedAuthority> autorities, and we pass in our User's roles.
		// That is why we implemented GrantedAuthority in our Role Entity.

		return user
				.map(u -> new org.springframework.security.core.userdetails.User(
					u.getEmail(), u.getPassword(), u.getRoles()))
				.orElseThrow(() -> new UsernameNotFoundException("User not found for Email '%s'".formatted(email)));
	}
}
