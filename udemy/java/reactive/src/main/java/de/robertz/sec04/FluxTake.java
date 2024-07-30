package de.robertz.sec04;

import java.util.stream.IntStream;

import de.robertz.common.Util;
import reactor.core.publisher.Flux;

public class FluxTake {
	public static void main(String[] args) {
		// Let's remind ourselves how limit() works:
		IntStream.range(1, 10).limit(3).forEach(System.out::println); // Will output only 3 although range goes to 10

		// take() works exactly the same way (Exactly the same as in kotlin too)
		Flux.range(1, 10).take(3).subscribe(Util.subscriber("take"));
		// Remember, take() here is a PROCESSOR (Sub + Pub in one)

		// Now let's observe something interesting...
		Flux.range(1, 10)
				.log("Take Log")
				.take(3)
				.log("Subscribe Log")
				.subscribe(Util.subscriber("take"));

		/*
		* This is nothing unusual and what we would expect actually:
		*     Subscribe Log                  : request(unbounded)
			... Take Log                       : | request(3)

		So when we start subscribe is unbounded. However, take is limited to 3.
		More interesting is how take() behaves after 3 items have been produced:

		...Take Log                       : | cancel()

		And immediately after:

		...Subscribe Log                  : onComplete()

		This shows how take() is a processor:
				- cancel() upstream
				- Invokes complete() downstream

		If we did take(moreThanAvailable), producer would just complete() as usual
		*/

		// takeWhile() can be passed a predicate and behaves similarly.
		// takeWhile() takes while the condition (predicate) is NOT met.
		// It's counterpart is takeUntil(), which takes until the condition IS met.
	}
}
