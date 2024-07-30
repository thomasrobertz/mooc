package de.robertz.sec04.helper;

import java.util.function.Consumer;

import com.github.javafaker.Faker;
import reactor.core.publisher.FluxSink;

public class Generator implements Consumer<FluxSink<String>> {

	private FluxSink<String> sink;

	@Override
	public void accept(FluxSink<String> stringFluxSink) {
			this.sink = stringFluxSink;
	}

	public void generate() {
		sink.next(Faker.instance().name().firstName());
	}
}
