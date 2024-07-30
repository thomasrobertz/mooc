package de.robertz.sec04;

import de.robertz.common.Util;
import reactor.core.publisher.Flux;

public class FluxGenerate {
	public static void main(String[] args) {
		// Generate is like create() but takes a SYNCHRONOUS sink, unlike create() which uses an async sink.
		Flux.generate(synchronousSink -> {
			synchronousSink.next(1);
			synchronousSink.complete();
		}).subscribe(Util.subscriber("Just one"));

		// If we run this we get an error. We can only emit one value.
		Flux.generate(synchronousSink -> {
			synchronousSink.next(1);
			synchronousSink.next(2);
			synchronousSink.complete();
		}).subscribe(Util.subscriber("More than one"));

		// So is Flux.generate a mono, since it only produces one item? No.
		// In create(), we get an async FluxSink. Once we have that, we are in control and can do with it whatever we want.
		// (Especially, keep on emitting items on demand)
		// Generate is completely opposite to that, only one sink is used, and Reactor keeps control.
		// If we were to remove the complete(), we would keep emitting one item from a new sync sink infinitely,
		// 	but of course based on the downstream demand (E.g. until canceled, or error)..
		// This is where take() comes in handy:
		Flux.generate(synchronousSink -> {
			synchronousSink.next(1);
		})
				.take(15) // = Downstream demand
				.subscribe(Util.subscriber("Gen"));
		// That explains also why the first example worked so nicely: because it complete()d.
	}
}
