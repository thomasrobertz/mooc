package de.robertz.functional.datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

/* This is a toy implementation only! It doesn't have consistent List operations.
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

	public static <T> FunctionalList<T> of(T ... ts) {
		FunctionalList<T> resultingList = TERMINAL;

		/* Traverse in reverse order.
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
		return new Concrete<>(e, this);
	}

	public FunctionalList<T> append(T e) {
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
		if(index == 0)
			return tail();
		return new Concrete<>(head(), tail().removeAt(index - 1));
	}

	public FunctionalList<T> insert(int index, T e) {
		if (index == 0) {
			return prepend(e);
		}
		if(index == length() - 1) {
			return this.tail().append(e);
		}
		return new Concrete<>(head(), tail().insert(index - 1, e));
	}

	public T getAt(final int index) {
		AtomicReference<T> valueAt = new AtomicReference<>();
		accept((i, e) -> { if (i == index) valueAt.set(e); });
		return valueAt.get();
	}

	public void accept(BiConsumer<Integer, ? super T> biConsumer) {
		T currentHead = head();
		FunctionalList<T> currentTail = tail();
		int length = length();
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
		if (right.equals(TERMINAL)) {
			return left;
		}
		return new Concrete<>(left.head(), concat(left.tail(), right));
	}

	public List<T> toList() {
		List<T> elements = new ArrayList<>();
		FunctionalList<T> t = tail();
		elements.add(head());
		while(!t.equals(TERMINAL)) {
			elements.add(t.head());
			t = t.tail();
		}
		return elements;
	}

	// Represents a chain link in the list.
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

	// Represents an empty list, or more precisely, the end of the list.
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
