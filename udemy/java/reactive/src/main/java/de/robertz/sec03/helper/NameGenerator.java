package de.robertz.sec03.helper;

import java.util.List;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;
import de.robertz.common.Util;
import reactor.core.publisher.Flux;

public class NameGenerator {
	public static List<String> nextList(int howMany) {
		return IntStream.rangeClosed(1, howMany)
				.mapToObj(i -> generateName())
				.toList();
	}
	public static Flux<String> nextFlux(int howMany) {
		return Flux.range(1, howMany)
				.map(i -> generateName());
	}
	private static String generateName() {
		// Simulate time-consuming op
		Util.sleep(1);
		Faker f = Faker.instance();
		return f.funnyName().name();
	}
}
