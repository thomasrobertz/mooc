package de.robertz.common;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public class AbstractHttpClient {
	private static final String BASE_URL = "http://localhost:7070";
	protected final HttpClient httpClient;

	public AbstractHttpClient() {
		var r = LoopResources.create("tr", 1, true);
		this.httpClient = HttpClient.create()
				.runOn(r)
				.baseUrl(BASE_URL);
	}
}
