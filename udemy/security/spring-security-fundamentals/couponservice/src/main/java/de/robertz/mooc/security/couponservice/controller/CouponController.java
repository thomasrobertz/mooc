package de.robertz.mooc.security.couponservice.controller;

import de.robertz.mooc.security.couponservice.model.Coupon;
import de.robertz.mooc.security.couponservice.repository.CouponRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CouponController {

	/*
	* We have to add the thymeleaf dependency for this to work.
	* If it is missing, you will simply get a 403 on / (serving index.html)!
	* */

	private final CouponRepository repository;

	public CouponController(CouponRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/showCreateCoupon")
	public String showCreateCoupon() {
		return "createCoupon";
	}

	@PostMapping("/saveCoupon")
	public String save(Coupon coupon) {
		repository.save(coupon);
		return "createResponse";
	}

	@GetMapping("/showGetCoupon")
	public String showGetCoupon() {
		return "getCoupon";
	}

	@PostMapping("/getCoupon")
	public ModelAndView getCoupon(String code) {
		ModelAndView mav = new ModelAndView("couponDetails");
		System.out.println(code);
		mav.addObject(repository.findByCode(code));
		return mav;
	}
}
