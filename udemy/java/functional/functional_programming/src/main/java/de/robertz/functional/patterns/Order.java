package de.robertz.functional.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Order {

	@Getter private List<String> cart = new ArrayList<>();
	@Getter private String targetDestination = "";
	@Getter private Boolean placed = false;

	public Order(List<String> cart, String targetDestination) {
		this.cart = cart;
		this.targetDestination = targetDestination;
	}

	public Order add(String item) {
		cart.add(item);
		// Could also use "return this" but it is to show explicit immutability
		return new Order(cart, targetDestination);
	}

	public Order deliver(String target) {
		this.targetDestination = target;
		return new Order(cart, targetDestination);
	}

	public Order placeOrder() {
		placed = true;
		return this;
	}

	public static Order functionalPlaceOrder(Function<Order, Order> placer) {
		return placer.apply(new Order());
	}

	public static Order functionalAutomaticPlaceOrder(Function<Order, Order> placer) {
		Order f = placer.apply(new Order());
		f.placeOrder();
		return f;
	}
}
