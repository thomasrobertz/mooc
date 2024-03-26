package de.robertz.functional.examples;

/*
 As thought experiment I was wondering if we could stay within an interface, immutable and stateless
 but still "load" the parameter upfront.
 */
public interface FrontLoadedChainedFunction<T> {

	static <T> FrontLoadedChainedFunction<T> load(T initial) {
		// Idea: Use a closure to keep a reference to the input "state"
		return t -> initial;
	}

	default FrontLoadedChainedFunction<T> then(FrontLoadedChainedFunction<T> next) {
		return t -> next.finish(this.finish(t));
	}

	T finish(T t);
}
