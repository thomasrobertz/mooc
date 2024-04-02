package de.robertz.security.eazybank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionsController {
	@GetMapping("/transactions")
	public String getTransactionHistory() {
		return "transactions";
	}
}
