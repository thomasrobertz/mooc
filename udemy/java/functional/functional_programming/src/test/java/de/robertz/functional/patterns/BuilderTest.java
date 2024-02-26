package de.robertz.functional.patterns;

import de.robertz.functional.patterns.Mobile.MobileBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuilderTest {
	@Test
	void createMobile() {
		// Note how lombok @Data or a java record is a very nicely fitting structure because it's immutable.
		MobileBuilder mb = new MobileBuilder();
		Mobile phone = mb.with(configuredPhone -> {
			mb.camera = 30;
			mb.ram = 512;
			mb.processor = "Zen";
			mb.screenSize = 6;
			mb.storage = 64;
		}).build();

		assertEquals(30, phone.camera);
		assertEquals(512, phone.ram);
		assertEquals("Zen", phone.processor);
		assertEquals(6, phone.screenSize);
		assertEquals(64, phone.storage);
	}

	@Test
	void createWithOnlySomeArgs() {
		// Shows how we don't need to create a lot of constructors or pass null values.
		MobileBuilder mb = new MobileBuilder();
		Mobile phone = mb.with(configuredPhone -> {
			mb.ram = 512;
			mb.processor = "Zen";
			mb.storage = 32;
		}).build();

		assertEquals(512, phone.ram);
		assertEquals("Zen", phone.processor);
		assertEquals(32, phone.storage);
	}
}
