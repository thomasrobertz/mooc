package de.robertz.functional.patterns;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Functional Object decorator
public class Burger {

	@Getter private String burger;

	public Burger() {
		burger = "Burger";
	}

	public Burger(String burger) {
		this.burger = burger;
	}

	public Burger addSalad() {
		burger += " Salad";
		return this;
	}
	public Burger addCheese() {
		burger += " Cheese";

		// Could also just return "this" but decorator specifically does not modifiy the source Object.
		return new Burger(burger);
	}
	public Burger addSauce() {
		burger += " Extra Sauce";
		return new Burger(burger);
	}

	@NoArgsConstructor
	@AllArgsConstructor
	public static class Preparation {

		// Inital standard burger function
		UnaryOperator<Burger> ingredients = b -> new Burger();

		public Burger with(Burger baseBurger) {
			// We have to make a copy of the base burger here so as not to apply to it as well.
			return new Burger(ingredients.apply(new Burger(baseBurger.burger)).getBurger());
		}
	}
}
