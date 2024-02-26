package de.robertz.functional.constructs;

public interface ChainableFunction<T> {

	T apply(T t);

	default ChainableFunction<T> then(ChainableFunction<T> next) {

		// Return a ChainedFunction lambda that processes the input (t) when called with apply.
		// Another ChainedFunction can then (sic) be added as next chain link to it.

		// Could be shortened to t -> next.apply(apply(t))
		// but for learning purposes I keep it expanded, easier to see what's going on.

		// Here we see that we are returning a ChainedFunction (as anonymous lambda).
		// 	It would not make sense to return a concrete ChainedFunction because then we would
		// 	have to implement apply and would lose all flexibility.
		return t -> { // Could also use generic type: (T t) -> {...

			// First execute this function's apply to calculate t
			T current = apply(t);

			// Then execute the next function's apply with the output of this function
			T chained = next.apply(current);

			// Return the result
			return chained;
		};
	}

}
