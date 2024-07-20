package de.robertz.sec02;

import de.robertz.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class EmptyAnDroppedError04 {

	private final static Logger log = LoggerFactory.getLogger(EmptyAnDroppedError04.class);

	// Demonstrate Mono.empty()
	public static void main(String[] args) {
		getUsername(1).subscribe(Util.subscriber());
		getUsername(2).subscribe(Util.subscriber());

		// Problem: Using overload w/o error. We get an ERROR: Operator called default onErrorDropped
		getUsername(66).subscribe(log::info);
	}

	public static Mono<String> getUsername(int userId) {
		return switch (userId) {
			case 1 -> Mono.just("TheUser");
			case 2 -> Mono.empty();
			default -> Mono.error(new RuntimeException("Invalid userId"));
		};
	}
}
