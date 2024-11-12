package de.robertz.common;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import org.reactivestreams.Subscriber;

public class Util {
	public static <T> Subscriber<T> subscriber() {
		return new DefaultSubscriber<T>(UUID.randomUUID().toString());
	}
	public static <T> Subscriber<T> subscriber(String name) {
		return new DefaultSubscriber<T>(name);
	}
	public static int sum(List<Integer> ints) {
		System.out.println("Performing long running task");
		return ints.stream().reduce((i, a) -> i + a).stream().findFirst().get();
	}
	public static void sleep(int s) {
		try {
			Thread.sleep(Duration.ofSeconds(s));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
