package de.robertz.functional.examples;

import java.util.function.IntUnaryOperator;

public class Yield {
	// I was wondering how I could simulate a simple linear (=increments of Z) sequence yielding generator.

	IntUnaryOperator generator;
	int index = 0;
	IntUnaryOperator incrementor;

	public Yield(IntUnaryOperator generator) {
		this.generator = generator;
		incrementor = i -> {
			if (i >= Integer.MAX_VALUE - 1) {
				return 0;
			}
			return ++i;
		};
	}

	public Yield(IntUnaryOperator generator, IntUnaryOperator incrementor, int startIndex) {
		this.generator = generator;
		this.incrementor = incrementor;
		this.index = startIndex;
	}

	public int yield() {
		int value = generator.applyAsInt(index);
		index = incrementor.applyAsInt(index);
		return value;
	}
}
