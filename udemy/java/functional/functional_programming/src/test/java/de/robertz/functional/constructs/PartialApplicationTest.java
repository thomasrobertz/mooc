package de.robertz.functional.constructs;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartialApplicationTest {

	@Test
	public void simplePartialApplication() {

		// A BiFunction that takes two integers and returns their sum
		BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;

		// Partially apply the sum function by fixing the first argument to 5
		// This creates a new Function that only needs one argument
		Function<Integer, Integer> addFive = b -> sum.apply(5, b);

		// Now, we can use addFive with a single argument
		assertEquals(15, addFive.apply(10));

		// This demonstrates partial application because we've taken a function that requires
		// two arguments (sum) and transformed it into a function that requires only
		// one argument (addFive) by fixing the first argument.
		// This is achieved without transforming sum into a series of single-argument
		// functions (currying), but rather by directly defining a new function that
		// encapsulates the partial application logic.

		// In the above example, we can say that addFive encapsulates a specific use case of sum.
		// And we see that the structure of the original function does not need to be altered.
		// With currying, we would have to construct one "big" function where we have no choice
		// in what functions are applied.
	}

	@Test
	void testMultiplePartialApplication() {

		// A function that takes two integers and returns a function that expects another integer.
		// Eventually, it sums all three integers.
		Function<Integer, Function<Integer, Function<Integer, Integer>>> sumThree =
				a -> b -> c -> a + b + c;

		// First partial application: Fix the first number to 10.
		Function<Integer, Function<Integer, Integer>> addTen = sumThree.apply(10);

		// Second partial application: Fix the second number to 5, using the result of the first application.
		Function<Integer, Integer> addFifteen = addTen.apply(5);

		assertEquals(60, addFifteen.apply(45));
	}

	@Test
	void decomposedPartialApplication() {
		// Kind of a trivial example.
		Function<Integer, Integer> multiplyByTwo = partialApply(2);

		// Use the partially applied function
		assertEquals(10, multiplyByTwo.apply(5));
		assertEquals(24, multiplyByTwo.apply(12));
	}

	// This method demonstrates partial application by taking one argument and returning a function that takes the next argument.
	public static Function<Integer, Integer> partialApply(int x) {
		return y -> multiply(x, y);
	}

	// A simple multiply function
	public static int multiply(int x, int y) {
		return x * y;
	}
}
