package de.robertz.sec03;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import de.robertz.common.Util;
import reactor.core.publisher.Flux;

public class NonBlockingInterval09 {
	public static void main(String[] args) {

		Flux.interval(Duration.ofMillis(500))
				.subscribe(Util.subscriber()); 	// Note that this will continue forever.
																				// If we don't want that, we should cancel() at some point.

		// If you run the above, the main thread (1) will exit immediately, and we will not see
		// anything.
		// So we should block the thread:
		Util.sleep(2);

		// So how can we run code to actually do something useful?
		// ->
//		Flux.interval(Duration.ofMillis(500))
//				.map(i -> /* some code */)
//				.subscribe(Util.subscriber());

		// Some more methods (similar to Mono)
		Flux.empty().subscribe(Util.subscriber("EMPTY"));
		Flux.fromStream(Stream.of(6, 7, 8)).subscribe(Util.subscriber("Stream"));
		Flux.defer(() -> Flux.fromIterable(List.of("a", "b"))).subscribe(Util.subscriber("Deferred"));
		Flux.error(new RuntimeException("Flux ERROR")).subscribe(Util.subscriber());

		// (1) Actually, the above will run in another separate child thread
	}
}
