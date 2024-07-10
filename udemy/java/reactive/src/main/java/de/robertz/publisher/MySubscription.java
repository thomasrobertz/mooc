package de.robertz.publisher;

import com.github.javafaker.Faker;
import de.robertz.subscriber.MySubscriber;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This is not meant aa a reference implementation, just as a demo!
public class MySubscription implements Subscription {
	private final static Logger log = LoggerFactory.getLogger(MySubscriber.class);
	private final Subscriber<? super String> subscriber;
	private boolean isCancelled;

	private boolean simulateError = false;

	private final Faker dataSource = Faker.instance();

	// We will not create as many items as requested
	private final static Integer MAX_ITEMS = 10;
	private Integer producedItemsCount = 0;

	public MySubscription(Subscriber<? super String> subscriber) {
		this.subscriber = subscriber;
	}

	public void simulateError() {
		this.simulateError = true;
	}

	@Override
	public void request(long l) {
		if(!isCancelled) {
			log.info("Subscriber requested {} items", l);
			for(long i = 0; (i < l) && (producedItemsCount < MAX_ITEMS); i++) {
				subscriber.onNext(i + ": " + dataSource.internet().emailAddress());
				producedItemsCount++;
				if(simulateError) {
					isCancelled = true;
					subscriber.onError(
						new RuntimeException("Simulated an error."));
					return;
				}
			}
			if (producedItemsCount >= MAX_ITEMS) {
				subscriber.onComplete();
				isCancelled = true;
			}
		}
	}

	@Override
	public void cancel() {
		log.info("Subscriber has cancelled");
		isCancelled = true;
	}
}
