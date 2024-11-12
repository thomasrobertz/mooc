package de.robertz.common;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultSubscriber<T> implements Subscriber<T> {

	private final static Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);
	private String name;

	public DefaultSubscriber(String name) {
		this.name = name;
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		log.info("{} requested items.", name);
		subscription.request(Long.MAX_VALUE);
	}
	@Override
	public void onNext(T dataItem) {
		log.info("{}: {}", name, dataItem);
	}
	@Override
	public void onError(Throwable throwable) {
		log.error("Error on {}: {}", name, throwable.getMessage());
	}
	@Override
	public void onComplete() {
		log.info("{} Received onComplete", name);
	}
}
