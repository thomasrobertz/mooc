package de.robertz.security.eazybank.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
	@PostMapping("/contact")
	public String createMessage() {
		return "msg created";
	}
}
