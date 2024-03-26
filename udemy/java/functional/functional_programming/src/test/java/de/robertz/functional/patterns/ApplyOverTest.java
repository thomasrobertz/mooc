package de.robertz.functional.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This is reminiscent of the Iterator pattern.
// But it's pretty much a standard practice in FP to apply a function over a list of elements.
public class ApplyOverTest {

	List<Integer> elements = IntStream.range(0, 9).boxed().toList();

	@Test
	void appliesToEach() {

		// Interesting to see:
		// 		-In the imperative loop we say "how"
		// 		-In the functional "loop" we say "what"
		//		-In the imperative loop we give start and end boundaries to loop over
		//		-In the functional "loop" we just iterate over the elements "freely"
		//			Though there are some ways to control the iteration, like skip() and limit()
		//			And a "fake" way, see below
		// We also see the explicit state management (i is manually incremented) in the imperative
		// approach, where the functional approach limits/prevents such side effects.

		List<Integer> classicLoopResults = new ArrayList<>();
		List<Integer> streamResults = new ArrayList<>();

		// Classic loop
		for(int i = 0; i < elements.size(); i++) {
				classicLoopResults.add(elements.get(i) * 2);
		}

		// Functional stream
		elements.forEach(e -> streamResults.add(ApplyOver.apply(e, i -> i * 2)));

		assertEquals(8, streamResults.get(4));
		assertEquals(16, streamResults.get(8));
		assertEquals(classicLoopResults, streamResults);
	}

	@Test
	void fakeImperativeLoop() {

		// Use an IntSream as "provider" for indexes.
		List<String> elements = List.of("a", "b", "c", "d", "e", "f", "g", "h");
		StringBuilder b = new StringBuilder();

		// Could also do more filtering
		IntStream.range(2, 7).forEach(i -> b.append(elements.get(i)).append(i));

		assertEquals("c2d3e4f5g6", b.toString());
	}

	@Test
	void strategy() {

		assertEquals(List.of(0, 1, 2, 3, 4), elements.stream().filter(lessThan5Strategy).toList());
		assertEquals(List.of(0, 2, 4, 6, 8), elements.stream().filter(isEvenStrategy).toList());

		assertEquals(List.of(2, 4), elements.stream()
				.filter(lessThan5Strategy
						.and(isEvenStrategy)
						.and(biggerThan0Strategy))
				.toList());
	}

	Predicate<Integer> lessThan5Strategy = (i) -> i < 5;
	Predicate<Integer> isEvenStrategy = (i) -> i % 2 == 0;
	Predicate<Integer> biggerThan0Strategy = (i) -> i > 0;
}
