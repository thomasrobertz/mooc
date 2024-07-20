package de.robertz.sec02.client;

import de.robertz.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {
	public Mono<String> getProduct(String productId) {
		return httpClient.get().uri("/demo01/product/" + productId)
				.responseContent()
				.asString()// This is a Flux<String>
				.next(); // Like findFirst. Converts Flux into Mono.
	}
}
