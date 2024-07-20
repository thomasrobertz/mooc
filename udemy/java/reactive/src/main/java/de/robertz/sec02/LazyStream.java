package de.robertz.sec02;

import java.util.stream.Stream;

import de.robertz.sec01.subscriber.MySubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LazyStream {
	private final static Logger log = LoggerFactory.getLogger(LazyStream.class);

	public void noop() {
		// Won't do anything, is lazy.
		// Reactive is similar.
		Stream.of(1).peek(l -> log.info(l.toString()));
	}

	public void terminal() {
		// Lambda only gets processed once we all a terminal.
		// In reactive, this is usually subscribe().
		Stream.of(1).peek(l -> log.info(l.toString())).toList();
	}
}
