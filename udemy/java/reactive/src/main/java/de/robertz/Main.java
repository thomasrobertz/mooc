package de.robertz;

import de.robertz.publisher.MyPublisher;
import de.robertz.publisher.MySubscription;
import de.robertz.subscriber.MySubscriber;
import org.reactivestreams.Publisher;

public class Main {
	public static void main(String[] args) {
//		setup();
//		requestSome();
//		requestTooMany();
//		subscriberCancels();
		simulateError();
	}

	private static void setup() {
		Publisher p = new MyPublisher();
		MySubscriber s = new MySubscriber();
		p.subscribe(s);
	}

	private static void requestSome() {
		Publisher p = new MyPublisher();
		MySubscriber s = new MySubscriber();
		p.subscribe(s);
		s.getSubscription().request(3L);
	}

	private static void requestTooMany() {
		Publisher p = new MyPublisher();
		MySubscriber s = new MySubscriber();
		p.subscribe(s);
		s.getSubscription().request(13L);
	}

	private static void subscriberCancels() {
		Publisher p = new MyPublisher();
		MySubscriber s = new MySubscriber();
		p.subscribe(s);
		s.getSubscription().request(3L);
		s.getSubscription().cancel();
		s.getSubscription().request(3L);
	}

	private static void simulateError() {
		Publisher p = new MyPublisher();
		MySubscriber s = new MySubscriber();
		p.subscribe(s);
		((MySubscription) s.getSubscription()).simulateError();
		s.getSubscription().request(3L);
	}
}