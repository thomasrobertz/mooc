package de.robertz.functional.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.robertz.functional.datastructures.FunctionalQueue.Consumed;
import org.junit.jupiter.api.Test;

import static de.robertz.functional.datastructures.FunctionalQueue.emptyQueue;
import static org.junit.jupiter.api.Assertions.*;

class FunctionalQueueTest {

	FunctionalQueue<String> empty = emptyQueue();

	// Resulting Queue:
	// front:	{"Functional", { }}
	// rear:	{"World", {"Hello", {"Queue", { }}}}
	FunctionalQueue<String> fq = empty
			.enqueue("Functional")
			.enqueue("Queue")
			.enqueue("Hello")
			.enqueue("World");

	@Test
	void consumeTest() {

		Optional<Consumed<String>> dOptional = fq.consume();

		if (dOptional.isPresent()) {

			Consumed<String> d = dOptional.get();

			assertEquals("Functional", d.consumed());
			d = d.rest().consume().get();
			assertEquals("Queue", d.consumed());
			d = d.rest().consume().get();
			assertEquals("Hello", d.consumed());
			d = d.rest().consume().get();
			assertEquals("World", d.consumed());

			assertTrue(d.rest().consume().isEmpty());
		}
		else {
			fail();
		}
	}

	@Test
	void acceptTest() {

		List<String> resultingList = new ArrayList<>();
		fq.accept(resultingList::add);

		assertEquals(List.of("Functional", "Queue", "Hello", "World"), resultingList);
	}

	@Test
	void acceptOnDequeueTest() {

		FunctionalQueue<String> dequeue = fq.dequeue();

		List<String> resultingList = new ArrayList<>();
		dequeue.accept(resultingList::add);

		assertEquals(List.of("Queue", "Hello", "World"), resultingList);
	}

	@Test
	void lengthTest() {
		assertEquals(4, fq.length());
	}

	@Test
	void isEmptyTest() {
		assertFalse(fq.isEmpty());
		assertTrue(emptyQueue().isEmpty());
	}

	@Test
	void headTest() {
		assertEquals("Functional", fq.getFront().head());
		FunctionalQueue<String> dequeue = fq.dequeue();
		assertEquals("Queue", dequeue.getFront().head());
	}
}