package de.robertz.functional.constructs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainableFunctionTest {
	@Test
	@SuppressWarnings({"unchecked"})
	void calculatesApplyResult() {
		assertEquals(4, ((ChainableFunction<Integer>) x -> ++x).apply(3));
	}

	@Test
	void calculatesThenApplyResult() {

		ChainableFunction<Integer> addOne = i-> i + 1;
		ChainableFunction<Integer> addTwo = i -> i + 2;
		ChainableFunction<Integer> doubleIt = i -> i * 2;

		assertEquals(16,
				addOne
						.then(addTwo)
						.then(doubleIt)
						.apply(5));
	}

	@Test
	void lambdaCanBePassed_Directly() {

		// Not so much of a test, rather to show that just as well a
		// type inferable matching functional interface can be passed into then().

		ChainableFunction<Integer> addOne = i-> i + 1;
		ChainableFunction<Integer> addTwo = i -> i + 2;

		assertEquals(16,
				addOne
						.then(addTwo)
						.then(i -> i * 2)
						.apply(5));
	}

	@Test
	void orderDoesntMatter() {
		ChainableFunction<Boolean> toggleOnce = b -> !b;
		ChainableFunction<Boolean> toggleAgain = b -> !b;

		assertEquals(toggleOnce.then(toggleAgain).apply(true),
				toggleAgain.then(toggleOnce).apply(true));
	}

	@Test
	void cannotPassNullIntoChain() {
		assertThrows(NullPointerException.class, () -> {
			ChainableFunction<Integer> addOne = i -> i + 1;
			addOne.then(null).apply(6);
		});
	}
}