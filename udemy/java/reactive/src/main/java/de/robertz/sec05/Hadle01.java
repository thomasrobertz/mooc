package de.robertz.sec05;

import de.robertz.common.Util;
import reactor.core.publisher.Flux;

public class Hadle01 {
	public static void main(String[] args) {

		Flux<Integer> fi = Flux.range(1, 5);

		// handle takes a BiConsumer, returns a Flux.
		Flux handlerFlux = fi.handle((i, sink) -> {
			sink.error(new RuntimeException("err"));
		});

		// So if we don't actually subscribe to the handler Flux, we won't see any error
		//fi.subscribe(Util.subscriber("fi"));

		onlyAllowSome();
	}

	static void onlyAllowSome() {
		Flux<Integer> fi = Flux.range(1, 10);
		fi.handle((i, s) -> {
			switch(i) { // Some requirement
				case 1 -> s.next(1);
				case 2 -> s.next(-2);
				case 3 -> { }
				case 7 -> s.next(77);
				case 9 -> s.error(new RuntimeException("why 9?"));
				default -> { }
			}
		}).subscribe(Util.subscriber("handler"));
	}
}
