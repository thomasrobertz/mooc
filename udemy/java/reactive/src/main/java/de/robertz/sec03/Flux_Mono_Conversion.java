package de.robertz.sec03;

import de.robertz.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Flux_Mono_Conversion {
	public static void main(String[] args) {
		// Mono to Flux

		//save(getUserName(1)); Of course this will not compile

		save(Flux.from(getUserName(1)));
		save(Flux.from(getUserName(100)));

		// Flux to Mono
		Flux<Integer> ftm = Flux.range(1, 10);
		ftm.next().subscribe(Util.subscriber("ftm"));
		// Can also do Mono.from(some_Flux)
	}

	private static Mono<String> getUserName(int id) {
		return switch(id) {
			case 1 -> Mono.just("Sam");
			case 2 -> Mono.just("Bill");
			case 3 -> Mono.just("Joe");
			default -> Mono.error(() -> new IllegalArgumentException(
					"Unexpected value: " + id));
		};
	}

	private static void save(Flux<String> ns) {
		ns.subscribe(Util.subscriber("save"));
	}
}
