package de.robertz.sec04;

import com.github.javafaker.Country;
import com.github.javafaker.Faker;
import de.robertz.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class FluxCreate_01 {
	public static void main(String[] args) {
		Flux.create(sink -> {
			sink.next(1);
			sink.next(2);
			sink.complete();
		})
				.subscribe(Util.subscriber("simple"));

		Flux.create(s -> {
			String country;
			do {
				country = Faker.instance().country().name();
				s.next(country);
			} while (!country.equalsIgnoreCase("canada"));
		}).subscribe(Util.subscriber("cond"));
	}
}
