package de.robertz.functional.constructs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrontLoadedChainableFunctionTest {

	@Test
	void calculatesFrontLoadedResult() {
		assertEquals("{'Hello'}",
				FrontLoadedChainedFunction.load("Hello")
					.then("'%s'"::formatted)
					.then("{%s}"::formatted)
					.finish(null));	// A little ugly but serves for demonstration purposes.
															// Of course at this stage it's a theoretical approach.
															// If I could just remove the null param I would be happy with it.
	}
}