package de.robertz.functional.datastructures;

import java.util.ArrayList;
import java.util.List;

import de.robertz.functional.datastructures.FunctionalList.Concrete;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalListTest {

	@Test
	void prependTest() {
		FunctionalList<Integer> fl = Concrete.of(2, 6, 9).prepend(17);
		List<Integer> collected = new ArrayList<>();
		fl.accept(collected::add); // Possible since List has add(int index, E element)
		assertEquals(List.of(17, 2, 6, 9), collected);
	}

	@Test
	void appendTest() {
		FunctionalList<Integer> fl = FunctionalList.of(2, 6, 9).append(17);
		List<Integer> collected = new ArrayList<>();
		fl.accept(collected::add);
		assertEquals(List.of(2, 6, 9, 17), collected);
	}

	@Test
	void insertAtTest() {
		FunctionalList<Integer> fl = FunctionalList.of(2, 6, 9);
		FunctionalList<Integer> fins = fl.insertAt(1, 17);

		List<Integer> collected = new ArrayList<>();
		fins.accept(collected::add);

		assertEquals(List.of(2, 17, 6, 9), collected);

		fins = fl.insertAt(0, 17);

		collected = new ArrayList<>();
		fins.accept(collected::add);

		assertEquals(List.of(17, 2, 6, 9), collected);
	}

	@Test
	void removeTest() {
		FunctionalList<Integer> fl = FunctionalList.of(2, 6, 9).remove(6);
		List<Integer> collected = new ArrayList<>();
		fl.accept(collected::add);
		assertEquals(List.of(2, 9), collected);
	}

	@Test
	void removeAtTest() {
		FunctionalList<Integer> fl = FunctionalList.of(2, 6, 9, 11).removeAt(2);
		List<Integer> collected = new ArrayList<>();
		fl.accept(collected::add);
		assertEquals(List.of(2, 6, 11), collected);
	}

	@Test
	void getAtTest() {
		assertEquals("c",
				FunctionalList.of("a", "b", "c", "d").getAt(2));
	}

	@Test
	void reverseTest() {
		FunctionalList<Integer> fl = FunctionalList.of(2, 6, 9).reverse();
		List<Integer> collected = new ArrayList<>();
		fl.accept(collected::add);
		assertEquals(List.of(9, 6, 2), collected);
	}

	@Test
	void joinTest() {
		FunctionalList<Integer> fl1 = FunctionalList.of(2, 6, 9);
		FunctionalList<Integer> fl2 = FunctionalList.of(7, 16, 22);
		FunctionalList<Integer> j1 = fl1.join(fl2, fl1.length() - 1);

		List<Integer> collected = new ArrayList<>();
		List<Integer> collectedPreserved = new ArrayList<>();
		fl1.accept(collectedPreserved::add);
		j1.accept(collected::add);

		assertEquals(List.of(2, 6, 9), collectedPreserved);
		assertEquals(List.of(2, 6, 9, 7, 16, 22), collected);
	}

	@Test
	void toListTest() {
		FunctionalList<String> s = FunctionalList.of("x", "y", "z");
		assertEquals(List.of("x", "y", "z"), ((Concrete<String>) s).toList());
	}
}