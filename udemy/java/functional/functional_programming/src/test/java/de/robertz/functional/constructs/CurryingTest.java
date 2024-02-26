package de.robertz.functional.constructs;

import java.util.function.Function;

import de.robertz.functional.constructs.CurryInterfaces.ComposedCurrying;
import de.robertz.functional.constructs.CurryInterfaces.Currying;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurryingTest {

	@Test
	void currying_LooksLikeChaining_Or_Composition() {
		Function<Integer, Function<Integer, Integer>> f1 = u -> v -> u + v;
		assertEquals(8, f1.apply(3).apply(5));
	}

	@Test
	void weCanAlso_partiallyApply() {

		Function<Integer, Function<Integer, Integer>> f1 = u -> v -> u + v;

		// "Intermediate" result of the first applied function.
		// We can use this akin to a variable to use later.
		Function<Integer, Integer> f2 = f1.apply(3);

		assertEquals(8, f2.apply(5));
		assertEquals(100, f2.apply(97));
	}

	@Test
	void oneMoreLevel() {
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
		Currying f3 = f2.apply(2);					// Same as Function<Integer, Integer>

		assertEquals(14, f3.apply(9));
		assertEquals(9, f3.apply(4));

		// One step back
		f3 = f2.apply(17);
		assertEquals(63, f3.apply(43));
	}
}
