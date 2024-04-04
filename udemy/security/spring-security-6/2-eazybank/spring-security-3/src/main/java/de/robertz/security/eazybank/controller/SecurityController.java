package de.robertz.security.eazybank.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
	@GetMapping("/manual-logout")
	public String manualLogout(HttpServletRequest request) throws ServletException {
		request.getSession().invalidate();
		request.logout(); // Invalidates the session and clears authentication.
		SecurityContextHolder.clearContext();
		return "redirect:/dashboard";
	}
}
