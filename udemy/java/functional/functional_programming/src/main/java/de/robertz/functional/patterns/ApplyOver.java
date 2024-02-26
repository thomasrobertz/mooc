package de.robertz.functional.patterns;

import java.util.function.IntUnaryOperator;

public class ApplyOver {
	public static Integer apply(Integer element, IntUnaryOperator f) {
		return f.applyAsInt(element);
	}
}
