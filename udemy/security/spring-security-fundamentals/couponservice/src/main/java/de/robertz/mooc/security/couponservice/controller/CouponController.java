package de.robertz.mooc.security.couponservice.controller;

import de.robertz.mooc.security.couponservice.model.Coupon;
import de.robertz.mooc.security.couponservice.repository.CouponRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
public class CouponController {

	private final CouponRepository repository;

	public CouponController(CouponRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/coupon")
	public Coupon create(@RequestBody Coupon coupon) {
			return repository.save(coupon);
	}

	@GetMapping("/coupon/{code}")
	public Coupon get(@PathVariable("code") String code) {
		return repository.findByCode(code);
	}
}
