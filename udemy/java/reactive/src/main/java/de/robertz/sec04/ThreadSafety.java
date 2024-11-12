package de.robertz.sec04;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.github.javafaker.Faker;
import de.robertz.common.Util;
import reactor.core.publisher.Flux;

public class ThreadSafety {
	public static void main(String[] args) {
		//notThreadSafe();
		threadSafeFlux();
	}

	static void notThreadSafe() {
		List<Integer> x = new ArrayList<>();
		Runnable r = (() -> {
			IntStream.range(1, 500).forEach(x::add);
		});
		for(int u = 0; u < 10; u++) {
			//new Thread(r).start(); old, Java 17 way:
			Thread.ofPlatform().start(r);
		}
		Util.sleep(5);
		System.out.println("Size " + x.size()); // Will probably not equal 10*500
	}

	static void threadSafeFlux() {
		List<String> x = new ArrayList<>();
		Flux.create(sink -> {
			IntStream.of(1, 20).forEach(n -> x.add(Faker.instance().name().firstName()));
		}).subscribe();

		Util.sleep(5);
		System.out.println(x);
	}
}
