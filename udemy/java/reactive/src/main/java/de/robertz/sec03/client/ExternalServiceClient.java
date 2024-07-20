package de.robertz.sec03.client;

import de.robertz.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class ExternalServiceClient extends AbstractHttpClient {
	public Flux<String> getRandomNames() {
		return httpClient.get().uri("/demo02/name/stream")
				.responseContent()
				.asString(); // This is a Flux<String>
	}
}
