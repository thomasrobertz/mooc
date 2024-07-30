package de.robertz.sec04;

import de.robertz.common.Util;
import de.robertz.sec04.helper.Generator;
import reactor.core.publisher.Flux;

public class RefactoredToGenerator {
	public static void main(String[] args) {
		Generator g = new Generator();
		Flux<String> f = Flux.create(g);
		f.subscribe(Util.subscriber("gen"));

		// Somewhere esle in code that can access g
		for(int n = 0; n < 10; n++) {
			g.generate();
		}
	}
}
