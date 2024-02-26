package de.robertz.functional.constructs;

import java.util.function.Function;

public class CurryInterfaces {
	interface Currying extends Function<Integer, Integer> { }
	interface ComposedCurrying extends Function<Integer, Currying> { }
}
