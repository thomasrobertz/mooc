package de.robertz.functional.constructs;

import java.util.function.BiFunction;
import java.util.function.Function;

import de.robertz.functional.constructs.CurryInterfaces.ComposedCurrying;
import de.robertz.functional.constructs.CurryInterfaces.Currying;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Also see PartialApplicationTest
public class CurryingTest {

	@Test
	void currying_LooksLikeChaining_Or_Composition() {
		// We see here how multiple chained functions (Type declaration) and parameters (u -> v -> ...) are implemented.
		Function<Integer, Function<Integer, Integer>> f1 = u -> v -> u + v;
		// Interesting to see how, unlike in chaining or composition, we are forced to follow the implicit
		// order of 1. function apply returning 2. function apply, or always u -> v.
		// In other words, we cannot do f2 apply f1.
		// Along with ordering, also the functions themselves are fixed.
		//
		// The flexibility of currying lies in its ability to partially apply functions and
		// create new functions with some arguments pre-applied, but it does
		// 		NOT allow for reordering of these arguments
		// 		NOR skipping ahead in the application sequence without additional mechanisms.
		assertEquals(8, f1.apply(3).apply(5));
	}

	@Test
	void weCanAlso_partiallyApply() {

		// Though somewhat rigid in terms of order and functionality, curried functions can
		// be partially applied in a "static" way.

		// f1 is a function that takes an Integer (u) and returns another function.
		// This returned function takes an Integer (v) and returns an Integer.
		// Essentially, f1 is a function for adding two integers, but it's curried,
		// meaning it takes its arguments one at a time.
		Function<Integer, Function<Integer, Integer>> f1 = u -> v -> u + v;

		// "Intermediate" result of applying the first function with u=3.
		// f2 is now a function that takes an Integer (v) and returns the result of adding 3 to it.
		// This demonstrates partial application: f1 is partially applied with the first argument (u=3),
		// resulting in a new function that only needs the second argument (v) to produce a result.
		// How can it be that we get back a function from f1? Just look at the type definition of f1.
		Function<Integer, Integer> f2 = f1.apply(3);

		// Here, we apply the second argument (v=5) to the partially applied function f2,
		// which already has u=3 applied. This computes 3 + 5, resulting in 8.
		assertEquals(8, f2.apply(5));

		// Similarly, applying v=97 to f2 (where u=3 is already applied) computes 3 + 97, resulting in 100.
		assertEquals(100, f2.apply(97));
	}

	@Test
	void threeParametersExample() {

		// Same as above, just with three instead of two parameters.

		Function<Integer, Function<Integer, Function<Integer, Integer>>> f1 =
				u -> v -> w -> u + v + w;

		Function<Integer, Function<Integer, Integer>> f2 = f1.apply(3);
		Function<Integer, Integer> f3 = f2.apply(2);

		assertEquals(14, f3.apply(9));
		assertEquals(9, f3.apply(4));

		// One step back
		f3 = f2.apply(17);
		assertEquals(63, f3.apply(43));
	}

	@Test
	void withInterface() {
		// Same as above, just looks nicer reduced with functional interfaces.

		Function<Integer, ComposedCurrying> f1 =
				u -> v -> w -> u + v + w;

		ComposedCurrying f2 = f1.apply(3); // Same as Function<Integer, Currying>
		Currying f3 = f2.apply(2);				  // Same as Function<Integer, Integer>

		assertEquals(14, f3.apply(9));
		assertEquals(9, f3.apply(4));

		// One step back
		f3 = f2.apply(17);
		assertEquals(63, f3.apply(43));
	}

	@Test
	void curriedBiFunction() {
		// f1 is a Function that takes a single Integer (z) and returns a BiFunction.
		// This BiFunction takes two Integer arguments (x and y) and returns an Integer.
		Function<Integer, BiFunction<Integer, Integer, Integer>> f1 = z -> (x, y) -> (x + y) * z;
		assertEquals(129, f1.apply(3).apply(9, 34));
	}
}
