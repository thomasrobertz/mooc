package de.robertz.functional.constructs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComposableFunctionTest {
	@Test
	void testCompose() {
		ComposableFunction<String> translate = s -> {
			if (s.equals("ja")) {
				return "yes";
			}
			if (s.equals("nein")) {
				return "no";
			}
			return "";
		};

		ComposableFunction<String> bang = "%s!"::formatted;

		assertEquals("no!", bang.compose(translate).apply("nein"));
		assertEquals("yes!", bang.compose(translate).apply("ja"));

		// In this case, order matters
		assertEquals("", translate.compose(bang).apply("nein"));
	}

	@Test
	void orderDoesntMatter() {
		ComposableFunction<Boolean> toggleOnce = b -> !b;
		ComposableFunction<Boolean> toggleAgain = b -> !b;

		assertEquals(toggleOnce.compose(toggleAgain).apply(true),
				toggleAgain.compose(toggleOnce).apply(true));
	}
}