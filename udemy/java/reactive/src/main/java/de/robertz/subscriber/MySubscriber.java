package de.robertz.subscriber;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySubscriber implements Subscriber<String> {

	private final static Logger log = LoggerFactory.getLogger(MySubscriber.class);
	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
	}
	@Override
	public void onNext(String emailBody) {
		log.info("Body {}", emailBody);
	}
	@Override
	public void onError(Throwable throwable) {
		log.error("Error {}", throwable.getMessage());
	}
	@Override
	public void onComplete() {
		log.info("Received onComplete");
	}

	public Subscription getSubscription() {
		return subscription;
	}
}
