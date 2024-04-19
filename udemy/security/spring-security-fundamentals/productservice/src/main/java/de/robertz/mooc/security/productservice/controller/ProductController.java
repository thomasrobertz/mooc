package de.robertz.mooc.security.productservice.controller;

import de.robertz.mooc.security.productservice.model.Product;
import de.robertz.mooc.security.productservice.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

	private final ProductRepository repository;

	public ProductController(ProductRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/showCreateProduct")
	public String showCreateProduct() {
		return "createProduct";
	}

	@PostMapping("/saveProduct")
	public String save(Product product) {
		repository.save(product);
		return "createResponse";
	}

	@GetMapping("/showGetProduct")
	public String showGetProduct() {
		return "getProduct";
	}

	@PostMapping("/getProduct")
	public ModelAndView getProduct(String name) {
		ModelAndView mav = new ModelAndView("productDetails");
		mav.addObject(repository.findByName(name));
		return mav;
	}
}
