package de.robertz.sec04;

import com.github.javafaker.Faker;
import de.robertz.common.Util;
import de.robertz.sec01.subscriber.MySubscriber;
import reactor.core.publisher.Flux;
/*
* IMPORTANT
* Flux sink is thread safe, everything is internally synchronized.
* This also means conceptually, sink can be used only in "single subscriber" mode.
* It is still very useful though:
* 		Collect data from many threads into a sink. Imagine concurrent product requests, that we wish to log by user id, and write to DB.
* 		A single subscriber can now be served by the sink (producer) and write the requests safely one-by-one into the DB.
* */
public class FluxDownstreamDemand {
	public static void main(String[] args) {
		// First let's look at the default way to produce data
		//upFront();

		// Now we produce on demand
		Faker faker = Faker.instance();
		MySubscriber s = new MySubscriber();
		Flux.<String>create(sink -> {
			// sink.onRequest is like a callback

			sink.onRequest(request -> {
				// This is invoked whenever there is a request from the subscriber

				//for(int n = 0; n < 5; n++) { // This would work too but may still produce items even though the subscription was already canceled
				for(int n = 0; n < 5 && (!sink.isCancelled()); n++) { // Here (Better: At this time, or _some_ time) we make sure the subscriber hasn't cancelled
					String name = faker.name().firstName();
					sink.next(name);
				}
			});
		}).subscribe(s);

		// Observe: Even though the for loop in onRequest creates 10 items, we only get two here...
		s.getSubscription().request(2);

		// ...and another two here...
		Util.sleep(2);
		s.getSubscription().request(2);

		// ...and even another 7, although the loop only goes to 5!
		Util.sleep(2);
		s.getSubscription().request(7);
		// ... this is because we are producing on demand.

		// But as usually once we cancel...
		s.getSubscription().cancel();
		// ... that's it!
		s.getSubscription().request(2); // No more
	}

	private static void upFront() {
		Faker faker = Faker.instance();
		MySubscriber s = new MySubscriber();
		Flux.<String>create(sink -> {
			for(int n = 0; n < 10; n++) {
				String name = faker.name().firstName();
				sink.next(name);
			}
			sink.complete();
		}).subscribe(s);

		// If we were to cancel immediately, Flux will still have created everything
		// try it: s.getSubscription().cancel();
		// 		IMPORTANT:
		// 		Flux stores the created items in a que.
		//		This also means: The Producer does NOT wait for the consumer to subscribe.
		//		And the subscriber does not know/care about this.
		//		However, the ASSUMPTION is, that at some point, the consumer will begin requesting items.
		//		The que can hold up to MAX_INT items.
		//		If no items are being requested, we call the result (Que filling up):
		//					BACKPRESSURE.
		// This however can be helpful in some cases where we want to create things upfront
		// and let the subscriber get it whenever he wants:

		Util.sleep(2);
		s.getSubscription().request(2);

		Util.sleep(2);
		s.getSubscription().request(2);

		Util.sleep(2);
		s.getSubscription().request(2);

		// Maybe subscriber is done and doesn't need any more data
		// This would mean the que is flushed.
		s.getSubscription().cancel();
	}
}
