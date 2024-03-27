package de.robertz.functional.datastructures;

import java.util.function.Consumer;

import static de.robertz.functional.datastructures.FunctionalList.TERMINAL;

// FIFO Queue
public class FunctionalQueue<T> {
	private final FunctionalList<T> front;
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

	public void accept(Consumer<T> consumer) {
		T e = front.head();
		FunctionalList<T> temp = FunctionalList.concat(front.tail(), rear.reverse());
		while(!temp.equals(TERMINAL)) {
			consumer.accept(e);
			e = temp.head();
			temp = temp.tail();
		}
	}
}
