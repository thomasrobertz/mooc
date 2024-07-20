package de.robertz.sec03;

import java.util.List;
import java.util.stream.Stream;

import de.robertz.common.Util;
import reactor.core.publisher.Flux;

public class Fluxes {
	public static void main(String[] args) {
		// Simplest flux
		Flux.just().subscribe();

		// Emitting values
		Flux.just(1, 2, "Thomas", 4).subscribe(System.out::println);

		// Multiple subscribers
		Flux<Integer> fms = Flux.just(1, 2, 3, 4);
		fms.subscribe(Util.subscriber("S1"));
		fms.subscribe(Util.subscriber("S2"));

		// Filtering etc.
		fms.filter(x -> x % 2 == 0).map(x -> x * 20)
				.subscribe(Util.subscriber("EVEN20"));

		// From Array etc.
		List<Integer> l = List.of(7, 8, 4, 5);
		Flux.fromIterable(l).subscribe(Util.subscriber());

		// From stream
		Stream<String> lstr = Stream.of("a", "b", "c").map(s -> s + "*");
		Flux.fromStream(lstr).subscribe(Util.subscriber("str"));
		// Stream is now terminated. We will get an exception if trying to add a new subscriber!
		// We can do that with a supplier:
		Flux<String> fsupplier = Flux.fromStream(() -> List.of("x", "y", "z").stream());
		fsupplier.subscribe(Util.subscriber("multstr1"));
		fsupplier.subscribe(Util.subscriber("multstr2"));

		// Range
		Flux.range(69, 6).subscribe(Util.subscriber("range"));

		// Log
		Flux.range(1,4)
				.log("range.sub") // helps to see what's going on.
					// Actually, log acts as a subscriber to the flux
					// and a publisher to whatever comes next (subscriber)
					// Meaning, it itself passes around subscription objects.
					// This of course also means it can only log things between its publisher and subscriber.
					//		pub -> log() -> sub
					// However, We could add log()s at multiple stages in flux processing.
					// In this case (our subscriber impl) it may request the max value of items from the flux! Keep that in mind.
					// You can kind of see that in the output:
					//		... : | request(unbounded)
					// So actually, log() is a processor (pub+sub in one).
				.subscribe(Util.subscriber("l"));
	}
}
