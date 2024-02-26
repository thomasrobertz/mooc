package de.robertz.functional.constructs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClosureTest {

	@Test
	void testClosure() {

		final int enclosingScopeVar = 73;

		Closure c = (s) -> {

			// Of course printing to the console is a bad, impure, no good, side effect...
			System.out.println("In %s".formatted(s));
			System.out.println("ESV is: %d".formatted(enclosingScopeVar));

			assertEquals(73, enclosingScopeVar);
		};

		c.closure("testClosure");
		anotherScope(c);
	}

	void anotherScope(Closure c) {
		// Closure "carries" the ESV with it. It has "closed" the scope off but "captured" it.
		c.closure("anotherScope");
	}
}