package de.robertz.functional.constructs;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LazyEvaluation {

	Supplier<Boolean> longRunningCall = () -> true;

	@Test
	void performsLongRunningCall() {
		assertLongRunningCallOnlyIfNeeded(true);
	}

	@Test
	void skipsLongRunningCall() {
		assertLongRunningCallOnlyIfNeeded(false);
	}

	private void assertLongRunningCallOnlyIfNeeded(boolean needed) {
		boolean taskWasPerformed = false;
		boolean didSomethingElse = false;
		if (needed) {
			taskWasPerformed = longRunningCall.get();
		} else {
			didSomethingElse = true;
		}
		assertEquals(needed, taskWasPerformed);
		assertNotEquals(needed, didSomethingElse);
	}
}
