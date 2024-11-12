package de.robertz.sec03;

import de.robertz.common.Util;
import de.robertz.sec03.client.ExternalServiceClient;

public class NonBlockingStreamingConsumer08 {
	public static void main(String[] args) {
		ExternalServiceClient client = new ExternalServiceClient();

		client.getRandomNames()
				.subscribe(Util.subscriber("rn1"));

		// Observe: Both subscribers get their own random names.
		client.getRandomNames()
				.subscribe(Util.subscriber("rn2"));

		// Observe that although we pause the thread, all the random names are already here.
		// Again, this is because we are non-blocking.
		Util.sleep(6);
	}
}
