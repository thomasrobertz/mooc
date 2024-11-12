package de.robertz.sec04;

import com.github.javafaker.Faker;
import de.robertz.common.Util;
import reactor.core.publisher.Flux;

public class FluxStateSupplier {
	public static void main(String[] args) {
		// Say we want to perform a time consuming operation in a Flux.
		// If we use generate, this would be executed each time (depending on downstream consumer demand).
		// So we can use State supplier overload.

		// For instance, stop at count 10 or if Canada:
		Flux.generate(
				() -> 0,
				(counter, synchronousSink) -> {
					String country = Faker.instance().country().name();
					synchronousSink.next(country);
					if(country.equals("Canada") || counter >= 10) {
						synchronousSink.complete();
					}
					return ++counter;
				}
		).subscribe(Util.subscriber("state"));
		//Another overload accepts a third parameter to f.i. close something or clean,
		// when we receive complete, error, cancel.
	}
}
