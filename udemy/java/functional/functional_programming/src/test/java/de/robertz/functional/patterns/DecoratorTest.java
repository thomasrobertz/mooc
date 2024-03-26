package de.robertz.functional.patterns;

import de.robertz.functional.patterns.Burger.Preparation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecoratorTest {

	@Test
	public void decorateBurger() {

		Burger standardBurger = new Burger();
		Preparation preparation = new Preparation();

		preparation.with(standardBurger);

		assertEquals("Burger", standardBurger.getBurger());
	}

	@Test
	public void prepareACoupleDifferentBurgers() {

		// A customer ordered:
		// 	1 standard burger
		// 	1 burger with cheese
		// 	2 burgers with salad
		// 	1 burger with cheese and extra sauce
		// 	1 burger with salad, cheese, extra sauce

		// Shows how instead of creating some burgers from scratch,
		// we "reuse" existing burgers as template and decorate them.
		// This is only possible if the original burger is not modified.
		Burger standardBurger = new Preparation().with(new Burger());
		Burger burgerWithCheese = new Preparation(Burger::addCheese).with(standardBurger);
		Burger burgerWithSalad1 = new Preparation(Burger::addSalad).with(standardBurger);
		Burger burgerWithSalad2 = new Preparation(Burger::addSalad).with(standardBurger);
		Burger burgerWithCheeseAndExtraSauce = new Preparation(Burger::addSauce).with(burgerWithCheese);
		Burger burgerWithAll = new Preparation(Burger::addSalad).with(burgerWithCheeseAndExtraSauce);

		assertEquals("Burger", standardBurger.getBurger());
		assertEquals("Burger Cheese", burgerWithCheese.getBurger());
		assertEquals("Burger Salad", burgerWithSalad1.getBurger());
		assertEquals("Burger Salad", burgerWithSalad2.getBurger());
		assertEquals("Burger Cheese Extra Sauce", burgerWithCheeseAndExtraSauce.getBurger());
		assertEquals("Burger Cheese Extra Sauce Salad", burgerWithAll.getBurger());
	}

	@Test
	public void prepareMultipleInOneStep() {

		Burger burger = new Preparation(b -> b
				.addSalad()
				.addCheese()
		)
		.with(new Burger());

		assertEquals("Burger Salad Cheese", burger.getBurger());
	}
}
