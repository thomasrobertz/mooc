package de.robertz.functional.patterns;

import de.robertz.functional.patterns.AC.Remote;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
	@Test
	public void controlACWithCommand() {

		AC ac = new AC();
		Remote r = new Remote();

		// Instead of needing to create a concrete class for each command, we pass lambdas.
		// This is not only simpler, but AC and Remote can stay decoupled.
		r.setCommand(() -> ac.increaseTemparature());

		assertEquals(30, ac.getTemperature());
		r.onButtonPressed();
		assertEquals(31, ac.getTemperature());
	}
}
