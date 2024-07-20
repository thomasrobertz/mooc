package de.robertz.sec02;

import de.robertz.common.Util;
import de.robertz.sec01.subscriber.MySubscriber;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoDemo {
	private final static Logger log = LoggerFactory.getLogger(MonoDemo.class);

	public void monoJust() {
		// Simplest Mono. This is a Mono<String>:
		Publisher<String> p = Mono.just("i am publisher");
		MySubscriber s = new MySubscriber();

		// The value for just() has to be evaluated immediately.

		// Remember since we are lazy, nothing will happen without a terminal op
		p.subscribe(s);
		// And also, we must request data (else nothing will happen):
		s.getSubscription().request(1);

		// We can see how convenient it is to work with Mono.just()
		// That's one of the reasons it exists in the first place:
		// To provide quick+easy creation of a publisher.
		// When working with reactive, there may be points when we
		// need to quickly create a publisher.
	}

	public void monoOverloads() {
		Mono<String> m = Mono.just("lambda subscribe");

		// Using an overload that doesn't use onComplete
		//m.subscribe(log::info);

		// This overload will print the messages
		m.subscribe(log::info, err -> log.error("Some error"),
				() -> { log.info("COMPLETED"); });

		// But we didn't request anything, why did this work?
		// -> In the overload, the request is done automaically.
		// If we wanted to use a subscription, we can use another
		// overload (but then we maybe would need to request manually again)
	}

	public void monoMap() {
		Mono.just("x").map(x -> x + "y")
				.subscribe(log::info);
	}

	public void defaultSubscriber() {
		Mono<String> m = Mono.just("x");
		m.subscribe(Util.subscriber());
	}
}
