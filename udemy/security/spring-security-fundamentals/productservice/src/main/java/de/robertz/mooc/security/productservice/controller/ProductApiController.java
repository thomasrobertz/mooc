package de.robertz.mooc.security.productservice.controller;

import java.util.Optional;

import de.robertz.mooc.security.productservice.dto.Coupon;
import de.robertz.mooc.security.productservice.model.Product;
import de.robertz.mooc.security.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api")
public class ProductApiController {

	private final ProductRepository repository;
	private final RestTemplate rest;

	@Value("${service.coupon.url}")
	private String couponServiceUrl;

	public ProductApiController(ProductRepository repository, RestTemplate rest) {
		this.repository = repository;
		this.rest = rest;
	}

	@PostMapping("/product")
	public Product create(@RequestBody Product product) {
		return repository.save(product);
	}

	@GetMapping("/product/{id}")
	public Product get(@PathVariable(value = "id") Long id) {
		return find(id).orElseThrow(
			() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
	}

	@GetMapping("/product/{id}/discount/{coupon}")
	public Product getDiscounted(@PathVariable(value = "id") Long id, @PathVariable(value = "coupon") String couponCode) {
		Coupon coupon = rest.getForObject(couponServiceUrl + couponCode, Coupon.class);
		return find(id).map(p -> {
			p.setPrice(p.getPrice().subtract(coupon.getDiscount()));
			return p;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
	}

	// Should be in a service
	private Optional<Product> find(Long id) {
		return repository.findById(id);
	}
}
