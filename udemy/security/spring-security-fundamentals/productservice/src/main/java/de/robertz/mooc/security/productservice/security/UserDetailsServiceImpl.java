package de.robertz.mooc.security.productservice.security;

import java.util.Optional;

import de.robertz.mooc.security.productservice.model.User;
import de.robertz.mooc.security.productservice.repository.UserRepository;
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
		return user
				.map(u -> new org.springframework.security.core.userdetails.User(
						u.getEmail(), u.getPassword(), u.getRoles())
				).orElseThrow(() -> new UsernameNotFoundException("User not found by email '%s'".formatted(email)));
	}
}
