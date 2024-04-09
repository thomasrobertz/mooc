package de.robertz.mooc.security.productservice.controller;

import de.robertz.mooc.security.productservice.dto.Coupon;
import de.robertz.mooc.security.productservice.model.Product;
import de.robertz.mooc.security.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("product")
public class ProductController {

	private final ProductRepository repository;
	private final RestTemplate rest;

	@Value("${service.coupon.url}")
	private String couponServiceUrl;

	public ProductController(ProductRepository repository, RestTemplate rest) {
		this.repository = repository;
		this.rest = rest;
	}

	@PostMapping("/product")
	public Product create(@RequestBody Product product) {
		Coupon coupon = rest.getForObject(
				couponServiceUrl + product.getCoupon(),
				Coupon.class);
		product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
		return repository.save(product);
	}

	@GetMapping("/product/{id}")
	public Product get(@PathVariable(value = "id") String id) {
		return repository.findById(Long.valueOf(id)).orElseThrow();
	}
}
