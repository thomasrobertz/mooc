package de.robertz.functional.patterns;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FluentTest {
	@Test
	public void fluentOrderPlacement() {

		// Before, without any fluent interface
		//		Fluent order = new Fluent();
		//		order.add("Shoes");
		//		order.add("Shirt");
		//		order.deliver("To me");
		//		order.placeOrder();

		assertOrderPlaced(new Order()
				.add("Shoes")
				.add("Shirt")
				.deliver("To me")
				.placeOrder());
	}

	@Test
	public void functionalFluentOrderPlacement() {
		// A little more concise, and of course functional style!
		assertOrderPlaced(Order.functionalPlaceOrder(o -> o
				.add("Shoes")
				.add("Shirt")
				.deliver("To me")
				.placeOrder())
		);
	}

	@Test
	public void functionalFluentAutomaticOrderPlacement() {
		assertOrderPlaced(Order.functionalAutomaticPlaceOrder(o -> o
				.add("Shoes")
				.add("Shirt")
				.deliver("To me"))
		);
	}

	private void assertOrderPlaced(Order order) {
		assertEquals(2, order.getCart().size());
		assertEquals("Shoes", order.getCart().get(0));
		assertEquals("To me", order.getTargetDestination());
		assertEquals(true, order.getPlaced());
	}
}