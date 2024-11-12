package de.robertz.sec01.subscriber;

import de.robertz.sec01.subscriber.publisher.MyPublisher;
import de.robertz.sec01.subscriber.publisher.MySubscription;
import de.robertz.sec01.subscriber.MySubscriber;
import org.reactivestreams.Publisher;

public class Main {
	public static void main(String[] args) {

		/* Reactive implementation:
		 * Reactive implementations are based on the Observer pattern.
		 * There are 3 participants: Publisher, Subscriber, Subscription
		 * Subscriber can subscribe (register) at publisher, and receives a Subscription instance
		 * With this instance, the subscriber can request data, cancel the subscription, or receive error events.
		 */

		/* Reactive protocol rules :
			1. The publisher doesn't have to publish anything (setup)
			2. The subscriber can request a number of data items (requestSome)
			3. The publisher will never return only as much data items as possible, but never more than the total number of data items (requestTooMany)
					When there are no more data items, it will invoke the subscribers onComplete or similar method.
			4. The subscriber can cancel (subscriberCancels)
			5. The publisher can invoke an error handler on the subscriber (simulateError)
		* */

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