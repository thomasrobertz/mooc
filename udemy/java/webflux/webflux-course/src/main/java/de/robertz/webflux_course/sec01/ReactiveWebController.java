package de.robertz.webflux_course.sec01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("reactive")
@Slf4j
public class ReactiveWebController {
	private final WebClient webClient = WebClient.builder()
			.baseUrl("localhost:7070")
			.build();

	@GetMapping("products")
	public Flux<Product> getProducts() {
		return webClient.get()
				.uri("/demo01/products")
				.retrieve()
				.bodyToFlux(Product.class)
				.doOnNext(p -> log.info("reactive received: {}", p)); // doOnNext executed after successful response
	}
}
