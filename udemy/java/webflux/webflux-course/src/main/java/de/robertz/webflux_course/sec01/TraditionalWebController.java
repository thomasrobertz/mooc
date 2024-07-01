package de.robertz.webflux_course.sec01;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("traditional")
@Slf4j
public class TraditionalWebController {
	private final RestClient restClient = RestClient.builder().build();

	@GetMapping("products")
	public List<Product> getProducts() {
		Object resultList = this.restClient
				.get()
				.uri("http://localhost:7070/demo01/products")
				.retrieve()
				.body(new ParameterizedTypeReference<>() {
				});
		log.info("Response: {}", resultList);
		return (List<Product>) resultList;
	}
}
