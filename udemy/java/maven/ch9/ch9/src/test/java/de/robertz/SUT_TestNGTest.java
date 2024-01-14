package de.robertz;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SUT_TestNGTest {
	@Test
	public void testGetHello() {
		assertEquals("Hello World.", new SUT().getHello());
	}
}
