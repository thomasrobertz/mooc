package de.robertz.mooc.security.couponservice.controller;

import de.robertz.mooc.security.couponservice.model.User;
import de.robertz.mooc.security.couponservice.repository.UserRepository;
import de.robertz.mooc.security.couponservice.security.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@Autowired
	SecurityService securityService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@GetMapping("/")
	public String showLoginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String login(String email, String password,
											HttpServletRequest request, HttpServletResponse response) {

		boolean loginSuccess = securityService.login(email, password, request, response);
		if (loginSuccess) {
			return "index";
		}
		return "login";
	}

	@GetMapping("/showRegistration")
	public String showRegistrationPage() {
		return "registerUser";
	}

	@PostMapping("/registerUser")
	public String register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login";
	}
}
