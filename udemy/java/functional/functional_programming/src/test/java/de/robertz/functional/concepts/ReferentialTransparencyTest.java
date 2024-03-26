package de.robertz.functional.concepts;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReferentialTransparencyTest {

	@Test
	void canReplaceCallWithValue() {
		// This seems a little trivial, it's just for demonstration purposes.

		BiFunction<Integer, Integer, Integer> add = Integer::sum;
		Function<Integer, Integer> timesTwo = x -> x * 2;
		Integer addedResult = add.apply(3, 7);

		assertEquals(20, timesTwo.apply(add.apply(3, 7)));
		assertEquals(20, timesTwo.apply(addedResult));
		assertEquals(20, timesTwo.apply(10));
	}
}
