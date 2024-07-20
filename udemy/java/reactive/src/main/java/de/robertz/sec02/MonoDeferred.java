package de.robertz.sec02;

import java.util.List;

import de.robertz.common.Util;
import reactor.core.publisher.Mono;

import static de.robertz.common.Util.sum;

public class MonoDeferred {
	public static void main(String[] args) throws InterruptedException {
//		createPublisher().subscribe(Util.subscriber());

		// Delay creation
		Mono.defer(MonoDeferred::createPublisher).subscribe(Util.subscriber());
	}

	static Mono<Integer> createPublisher() {
		System.out.println("Creating publisher itself is time consuming, even without subscriber");
		//Util.sleep(1);
		return Mono.fromSupplier(() -> sum(List.of(12, 13, 14)));
	}
}
