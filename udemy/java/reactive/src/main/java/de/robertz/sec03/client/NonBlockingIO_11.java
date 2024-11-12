package de.robertz.sec03.client;

import de.robertz.common.Util;
import de.robertz.sec02.client.ExternalServiceClient;

// Make sure /app/external-services.jar is running:
//    java -jar .\external-services.jar
public class NonBlockingIO_11 {
	public static void main(String[] args) {
		// The normalNonBlockingRequest request is already non blocking.
		// But to see the "big deal", we execute the request multiple times
		// in a loop.
		// Observe:
		//   We receive all requests (5 publishers, 5 subsribers) with just one thread (Configured with LoopResources).
		//   Even though we wait only 2 seconds we get 5 requests IN THE SAME SECOND.
		//   The requests are not necessarily sent in order
		// We could also send 50 or even 100 requests!
		// If we did this with traditional blocking requests, it would take 100 seconds or more! Or, you would need 100 threads.
		nonBlockingRequest();
	}

	static void nonBlockingRequest() {
		ExternalServiceClient client = new ExternalServiceClient();

		for(int n = 0; n < 5; n++) {
			client.getProduct(String.valueOf(n)).subscribe(Util.subscriber());
		}

		Util.sleep(2);
	}

	static void normalNonBlockingRequest() {
		ExternalServiceClient client = new ExternalServiceClient();
		client.getProduct("6").subscribe(Util.subscriber());

		// If we ran this now, we wouldn't see the product.
		// Because the client is non-blocking. So lets wait on the thread:
		Util.sleep(2); // 2 secs because the demo server already simulates a 1 second delay.
	}
}
