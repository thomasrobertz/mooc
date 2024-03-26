package de.robertz.functional.constructs;

@FunctionalInterface
public interface MultiparameterFunction<R, A, B, C, D> {
	R apply(A a, B b, C c, D d);
}
