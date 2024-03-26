package de.robertz.functional.constructs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiparameterFunctionTest {

	// In Java we only have BiFunction, here's an example of how to create a function with more than 2 parameters.

	@Test
	void testMultiparameterFunction() {

		MultiparameterFunction<Integer, Integer, Integer, Integer, Integer> npf =
				(a, b, c, d) -> a + b + c + d;

		assertEquals(29, npf.apply(3, 6, 8, 12));
	}
}
