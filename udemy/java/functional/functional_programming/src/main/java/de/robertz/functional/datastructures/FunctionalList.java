package de.robertz.functional.datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

/* This is a toy implementation only! It doesn't have consistent List operations.
 *
 * FunctionalList is like a chain.
 * Each chain has a head and a tail.
 * The tail is another FunctionalList, containing the remaining elements.

 ┌───────────┐
 │ Head: 1   │
 │           │
 │ ┌───────┐ │     ┌───────────┐
 │ │ Tail  ├─┼────►│ Head: 2   │
 │ └───────┘ │     │           │
 │  concrete │     │ ┌───────┐ │    ┌───────────┐
 └───────────┘     │ │ Tail  ├─┼───►│ Head: null│
									 │ └───────┘ │    │           │
									 │ concrete  │    │ ┌───────┐ │
									 └───────────┘    │ │ null  │ │
																		│ └───────┘ │
																		│ terminal  │
																		└───────────┘
 */
public abstract class FunctionalList<T> {

	public abstract T head();
	public abstract FunctionalList<T> tail();

	@SuppressWarnings("rawtypes")
	public static final Terminal TERMINAL = new Terminal();

	/**
	 * Construct a functional list from the given parameters.
	 *
	 * @param ts
	 * @return
	 * @param <T>
	 */
	public static <T> FunctionalList<T> of(T ... ts) {
		FunctionalList<T> resultingList = TERMINAL;

		/* Traverse td in reverse order.
				Example with ts 22, 10, 64:

				ts.length-1 is: 2

						 ┌─constructor─┐
						 │             │
						 v             v
				i		head					tail
				------------------------------
				2 	64						TERMINAL
				1 	10						64, TERMINAL
				0 	22						64, 10, TERMINAL
		 */
	 	for (int i = ts.length - 1; i >= 0; i--) {
			 resultingList = new Concrete<>(ts[i], resultingList);
		}
		return resultingList;
	}

	protected int length() {
		int length = 0;
		FunctionalList<T> current = this;
		while (!Objects.equals(current, TERMINAL)) {
			current = current.tail();
			length++;
		}
		return length;
	}

	public FunctionalList<T> prepend(T e) {
		// Prepend is quick
		return new Concrete<>(e, this);
	}

	public FunctionalList<T> append(T e) {
		// A bit of an expensive operation, could be optimized
		return reverse().prepend(e).reverse();
	}

	public FunctionalList<T> addAll(final Collection<? extends T> list){
		FunctionalList<T> resultList = this;
		for (T t : list) {
			resultList = resultList.append(t);
		}
		return resultList;
	}

	public FunctionalList<T> remove(T e) {
		if (length() == 0) {
			return this;
		}
		if (e.equals(head())) {
			return tail();
		}

		// Element not found yet, search recursively in tail
		return new Concrete<>(head(), tail().remove(e));
	}

	public FunctionalList<T> removeAt(int index) {
		if(index == 0) {
			return tail();
		}

		// Descend the tails until index is met
		return new Concrete<>(head(), tail().removeAt(index - 1));
	}

	public FunctionalList<T> insert(int index, T e) {
		if (index == 0) {
			return prepend(e);
		}
		if(index == length() - 1) {
			return this.tail().append(e);
		}
		// Similar to removeAt, descend tails
		return new Concrete<>(head(), tail().insert(index - 1, e));
	}

	public T getAt(final int index) {
		// A bit of a hack but works
		AtomicReference<T> valueAt = new AtomicReference<>();
		accept((i, e) -> { if (i == index) valueAt.set(e); });
		return valueAt.get();
	}

	public void accept(BiConsumer<Integer, ? super T> biConsumer) {
		T currentHead = head();
		FunctionalList<T> currentTail = tail();
		int length = length();

		// Loop over all elements
		for (int i = 0; i < length; i++) {
			biConsumer.accept(i, currentHead);
			currentHead = currentTail.head();
			currentTail = currentTail.tail();
		}
	}

	public FunctionalList<T> reverse() {
		FunctionalList<T> resultingList = TERMINAL;
		T current = head();
		FunctionalList<T> temp = this;

		// Also descend tails but prepend each element, resulting in a reversed list.
		while(!temp.equals(TERMINAL)) {
			resultingList = resultingList.prepend(current);
			temp = temp.tail();
			current = temp.head();
		}
		return resultingList;
	}

	public static <T> FunctionalList<T> concat(FunctionalList<T> left, FunctionalList<T> right) {

		if (left.equals(TERMINAL)) {
			return right;
		}

		/* Recurse this construction template:
		 *
		 * Create a new chain link with the left's head and the concatenation of it's tail
		 * (as new head in the recursive succession) and the right list.
		 *
		 * Example (Not all Terminals shown):
		 * 1. concat({2, {3}}, {7, {8}})	->	create and 1st recursive call with Concrete(2, concat({3}, {7, {8}}))
		 * 2. concat({3}, {7, {8}})				->	create and 2nd recursive call with Concrete(3, concat({ }, {7, {8}}))
		 * 3. concat({ }, {7, {8})				->	TERMINAL left, {7, {8}} returned into previous concat call.
		 * 4. (Returning from 2.) Concrete(3, concat({ }, {7, {8}})) 	yields Concrete(3, {7, {8}})
		 * 5. (Returning from 1.) Concrete(2, concat({3}, {7, {8}})) 	yields Concrete(2, { 3, {7, {8}}})
		 */
		return new Concrete<>(left.head(), concat(left.tail(), right));
	}

	public List<T> toList() {
		List<T> elements = new ArrayList<>();
		FunctionalList<T> t = tail();

		if (Objects.isNull(t)) {
			return elements;
		}

		elements.add(head());
		while(!t.equals(TERMINAL)) {
			elements.add(t.head());
			t = t.tail();
		}
		return elements;
	}

	// Represents a chain link (concrete element, that has a tail) in the list.
	public static class Concrete<T> extends FunctionalList<T> {

		private T head;
		private FunctionalList<T> tail;

		public Concrete(T t, FunctionalList<T> fl) {
			head = t;
			tail = fl;
		}

		@Override
		public T head() {
			return head;
		}

		@Override
		public FunctionalList<T> tail() {
			return tail;
		}
	}

	// Represents an empty list which is also the end of the list, doesn't have tail (or head)
	private static class Terminal<T> extends FunctionalList<T> {
		@Override
		public T head() {
			return null;
		}
		@Override
		public FunctionalList<T> tail() {
			return null;
		}
	}
}
