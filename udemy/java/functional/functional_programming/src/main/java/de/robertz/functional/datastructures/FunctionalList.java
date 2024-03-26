package de.robertz.functional.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

import de.robertz.functional.examples.Yield;
import lombok.Getter;

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

	public abstract FunctionalList<T> prepend(T e);
	public abstract FunctionalList<T> append(T e);
	public abstract FunctionalList<T> insertAt(int index, T e);
	public abstract FunctionalList<T> remove(T e);
	public abstract FunctionalList<T> removeAt(int index);
	public abstract void accept(BiConsumer<Integer, ? super T> consumer);
	public abstract FunctionalList<T> join(FunctionalList<T> other, int count);

	public abstract T getAt(int i);

	public abstract FunctionalList<T> reverse();

	// Represents a chain link in the list.
	public static class Concrete<T> extends FunctionalList<T> {

		private T head;
		private FunctionalList<T> tail;

		public Concrete(T t, FunctionalList<T> fl) {
			head = t;
			tail = fl;
		}

		// TODO move up into FunctionalList, get rid of all the extra abstract methods
		public FunctionalList<T> prepend(T e) {
			return new Concrete<>(e, this);
		}

		public FunctionalList<T> append(T e) {
			int l = length() - 1;
			FunctionalList<T> resultingList = TERMINAL;
			FunctionalList<T> append = new Concrete<>(e, TERMINAL);
			for(int n = l; n >= 0; n--) {
				if (n == l) {
					resultingList = new Concrete<>(getAt(n), append);
				} else {
					resultingList = new Concrete<>(getAt(n), resultingList);
				}
			}
			return resultingList;
		}

		public FunctionalList<T> remove(T e) {
			if (length() == 0) {
				return this;
			}
			if (e.equals(head)) {
				return tail;
			}

			// Element not found yet, search recursively in tail
			return new Concrete<>(head, tail.remove(e));
		}

		public void accept(BiConsumer<Integer, ? super T> biConsumer) {
			T currentHead = head;
			FunctionalList<T> currentTail = tail;
			int length = length();
			for (int i = 0; i < length; i++) {
				biConsumer.accept(i, currentHead);
				currentHead = currentTail.head();
				currentTail = currentTail.tail();
			}
		}

		@Override
		public FunctionalList<T> reverse() {
			FunctionalList<T> resultingList = TERMINAL;
			T current = head;
			FunctionalList<T> temp = this;
			while(!temp.equals(TERMINAL)) {
				resultingList = resultingList.prepend(current);
				temp = temp.tail();
				current = temp.head();
			}
			return resultingList;
		}

		public FunctionalList<T> reverseAlt() {
			FunctionalList<T> resultingList = TERMINAL;
			int l = length();
			for(int n = 0; n < l; n++) {
				resultingList = new Concrete<>(getAt(n), resultingList);
			}
			return resultingList;
		}

		public FunctionalList<T> insertAt(int index, T e) {
			if (index == 0) {
				return prepend(e);
			}
			int l = length();
			FunctionalList<T> resultingList = copy(0, index - 1).append(e);
			return resultingList.join(copy(index, l - 1), l - index - 1);
		}

//		public FunctionalList<T> insertAtRec(int index, T e){
//			if(index == 0)
//				return tail.prepend(e);
//			return new Concrete<T>(head, tail.insertAtRec(index-1, e));
//		}

		public FunctionalList<T> removeAt(int index) {
			int l = length();
			FunctionalList<T> resultingList = copy(0, index - 1);
			return resultingList.join(copy(index + 1, l - 1), l - index - 2); // -2 because we removed one
		}

		public T getAt(final int index) {
			AtomicReference<T> valueAt = new AtomicReference<>();
			accept((i, e) -> { if (i == index) valueAt.set(e); });
			return valueAt.get();
		}

		public FunctionalList<T> join(FunctionalList<T> other, int count) {
			FunctionalList<T> currentTail = tail;
			for(int n = 0; n <= count; n++) {
				// TODO Rewrite to not use getAt
				currentTail = currentTail.append(other.getAt(n));
			}
			return new Concrete<>(head, currentTail);
		}

		private FunctionalList<T> copy(int from, int to) {
			FunctionalList<T> resultingList = TERMINAL;
			for(int n = to; n >= from; n--) {
				resultingList = new Concrete<>(getAt(n), resultingList);
			}
			// TODO rewrite to not use getAt
			return resultingList;
		}

		public List<T> toList() {
			List<T> elements = new ArrayList<>();
			FunctionalList<T> t = tail;
			elements.add(head);
			while(!t.equals(TERMINAL)) {
				elements.add(t.head());
				t = t.tail();
			}
			return elements;
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

		@Override
		public FunctionalList<T> prepend(T e) {
			return this;
		}

		@Override
		public FunctionalList<T> append(T e) {
			return this;
		}

		@Override
		public FunctionalList<T> insertAt(int index, T e) {
			return null;
		}

		@Override
		public FunctionalList<T> remove(T e) {
			return null;
		}

		@Override
		public FunctionalList<T> removeAt(int index) {
			return null;
		}

		@Override
		public void accept(BiConsumer<Integer, ? super T> consumer) {

		}

		@Override
		public FunctionalList<T> join(FunctionalList<T> other, int count) {
			return null;
		}

		@Override
		public T getAt(int i) {
			return null;
		}

		@Override
		public FunctionalList<T> reverse() {
			return null;
		}
	}
}
