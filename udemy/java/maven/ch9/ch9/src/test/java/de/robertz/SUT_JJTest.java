package de.robertz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SUT_JJTest {

	@Test
	void getHello() {
		assertEquals("Hello World.", new SUT().getHello());
	}
}