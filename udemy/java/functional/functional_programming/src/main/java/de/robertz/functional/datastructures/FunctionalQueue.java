package de.robertz.functional.datastructures;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import lombok.Getter;

import static de.robertz.functional.datastructures.FunctionalList.TERMINAL;

// FIFO Queue, reusing the FunctionalList
public class FunctionalQueue<T> {
	@Getter
	private final FunctionalList<T> front;
	@Getter
	private final FunctionalList<T> rear;

	private FunctionalQueue() {
		front = TERMINAL;
		rear = TERMINAL;
	}

	private FunctionalQueue(FunctionalQueue<T> queue, T e) {
		if(queue.front.equals(TERMINAL)) {
			front = queue.front.prepend(e);
			rear = queue.rear;
		}
		else {
			front = queue.front;
			rear = queue.rear.prepend(e);
		}
	}

	public FunctionalQueue(FunctionalList<T> front, FunctionalList<T> rear) {
		if(front.equals(TERMINAL)) {
			// Reverse rear and make it front
			this.front = rear.reverse();
			this.rear = front;
		}
		else {
			// Leave unchanged
			this.front = front;
			this.rear = rear;
		}
	}

	public static <T> FunctionalQueue<T> emptyQueue() {
		return new FunctionalQueue<>();
	}

	public FunctionalQueue<T> enqueue(T e) {
		return new FunctionalQueue<>(this, e);
	}

	public FunctionalQueue<T> dequeue() {
		return new FunctionalQueue<>(front.tail(), rear);
	}

	public Optional<Consumed<T>> consume() {
		if (front.equals(TERMINAL)) {
			return Optional.empty();
		}
		return Optional.of(new Consumed<>(front.head(), dequeue()));
	}

	public void accept(Consumer<T> consumer) {
		T e = front.head();
		FunctionalList<T> temp = FunctionalList.concat(front.tail(), rear.reverse());
		do {
			consumer.accept(e);
			e = temp.head();
			temp = temp.tail();
		} while(temp != null);
	}

	public int length() {
		return front.length() + rear.length();
	}

	public boolean isEmpty() {
		return front.equals(TERMINAL) && rear.equals(TERMINAL);
	}

	record Consumed<T>(T consumed, FunctionalQueue<T> rest) { }
}
