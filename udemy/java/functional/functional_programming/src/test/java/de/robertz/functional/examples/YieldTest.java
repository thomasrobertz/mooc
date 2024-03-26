package de.robertz.functional.examples;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class YieldTest {

	@Test
	void increment() {
		Yield y = new Yield(i -> ++i);
		assertEquals(1, y.yield());
		assertEquals(2, y.yield());
		assertEquals(3, y.yield());
		assertEquals(4, y.yield());
		assertEquals(5, y.yield());
	}

	@Test
	void square() {
		Yield y = new Yield(i -> i * i);
		assertEquals(0, y.yield());
		assertEquals(1, y.yield());
		assertEquals(4, y.yield());
		assertEquals(9, y.yield());
		assertEquals(16, y.yield());
	}

	@Test
	void random() {
		// A bit pointless since we could use Random directly but illustrates the point.
		Yield y = new Yield(i -> new Random().nextInt(1, 10));

		// Good enough
		AtomicInteger sum = new AtomicInteger(0);
		IntStream.range(0, 20000).forEach(i -> {
			int rand = y.yield();
			assertTrue(rand >= 1 && rand < 10);
			sum.getAndAdd(rand);
		});

		double mean = (double) sum.get() / 20000;
		if((mean < 4.5) || (mean > 5.5)) {
			fail("Something is wrong");
		}
	}

	@Test
	void limit() {
		Yield y = new Yield(i -> ++i, i -> {
			if (i >= 28) {
				i = 20;
			}
			return i + 2;
		}, 19);

		// A bit ugly but illustrates the point. TODO Fix, would have to add more logic.
		assertEquals(20, y.yield());
		assertEquals(22, y.yield());
		assertEquals(24, y.yield());
		assertEquals(26, y.yield());
		assertEquals(28, y.yield());
		assertEquals(30, y.yield());
		assertEquals(23, y.yield());
		assertEquals(25, y.yield());
		assertEquals(27, y.yield());
		assertEquals(29, y.yield());
		assertEquals(23, y.yield());
	}
}
