package de.robertz.sec01.subscriber.publisher;

import de.robertz.sec01.subscriber.MySubscriber;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPublisher implements Publisher<String> {
	private final static Logger log = LoggerFactory.getLogger(MySubscriber.class);
	@Override
	public void subscribe(Subscriber<? super String> subscriber) {
		MySubscription sub = new MySubscription(subscriber);
		subscriber.onSubscribe(sub);
	}
}
