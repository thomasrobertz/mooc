package de.robertz.functional.constructs;

public interface ComposableFunction<T> {

	T apply(T t);

	default ComposableFunction<T> compose(ComposableFunction<T> next) {

		// Basically the exact same as ChainableFunction, just with reversed operation.

		return t -> {

			// First execute the next function's apply to calculate t
			T current = next.apply(t);

			// Then execute this function's apply with the output of the next function
			T composed = apply(current);

			// Return the result
			return composed;
		};
	}

}
