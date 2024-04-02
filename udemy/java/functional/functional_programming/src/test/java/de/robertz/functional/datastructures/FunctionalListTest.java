package de.robertz.functional.datastructures;

import java.util.List;

import de.robertz.functional.datastructures.FunctionalList.Concrete;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalListTest {

	@Test
	void prependTest() {
		FunctionalList<Integer> fl = Concrete.of(2, 6, 9).prepend(17);
		assertEquals(List.of(17, 2, 6, 9), fl.toList());
	}

	@Test
	void appendTest() {
		FunctionalList<Integer> fl = FunctionalList.of(2, 6, 9).append(17);
		assertEquals(List.of(2, 6, 9, 17), fl.toList());
	}

	@Test
	void insertTest() {
		FunctionalList<Integer> fl = FunctionalList.of(2, 6, 9);

		FunctionalList<Integer> fins = fl.insert(1, 17);
		assertEquals(List.of(2, 17, 6, 9), fins.toList());

		fins = fl.insert(0, 17);
		assertEquals(List.of(17, 2, 6, 9), fins.toList());

		fins = fl.insert(fl.length(), 17);
		assertEquals(List.of(2, 6, 9, 17), fins.toList());
	}

	@Test
	void addAllTest() {
		FunctionalList<String> a = FunctionalList.of("a", "b", "c");
		List<String> s = List.of("d", "e", "f");

		assertEquals(List.of("a", "b", "c", "d", "e", "f"),
				a.addAll(s).toList());
	}

	@Test
	void removeTest() {
		FunctionalList<Integer> fl = FunctionalList.of(2, 6, 9).remove(6);
		assertEquals(List.of(2, 9), fl.toList());
	}

	@Test
	void removeAtTest() {
		FunctionalList<Integer> fl = FunctionalList.of(2, 6, 9, 11).removeAt(2);
		assertEquals(List.of(2, 6, 11), fl.toList());
	}

	@Test
	void getAtTest() {
		assertEquals("c",
				FunctionalList.of("a", "b", "c", "d").getAt(2));
	}

	@Test
	void reverseTest() {
		FunctionalList<Integer> fl = FunctionalList.of(2, 6, 9).reverse();
		assertEquals(List.of(9, 6, 2), fl.toList());
	}

	@Test
	void concatTest() {
		FunctionalList<Integer> fl1 = FunctionalList.of(2, 3);
		FunctionalList<Integer> fl2 = FunctionalList.of(7, 8);
		FunctionalList<Integer> concat = FunctionalList.concat(fl1, fl2);

		assertEquals(List.of(2, 3), fl1.toList());
		assertEquals(List.of(7, 8), fl2.toList());
		assertEquals(List.of(2, 3, 7, 8), concat.toList());
	}

	@Test
	@SuppressWarnings("unchecked")
	void concatEdgeCasesTest() {

		FunctionalList<Integer> concat = FunctionalList.concat(
				FunctionalList.of(3, 4, 5),
				FunctionalList.of(7, 8));
		assertEquals(List.of(3, 4, 5, 7, 8), concat.toList());

		concat = FunctionalList.concat(
				FunctionalList.of(3, 4),
				FunctionalList.of(7, 8, 9));
		assertEquals(List.of(3, 4, 7, 8, 9), concat.toList());

		concat = FunctionalList.concat(FunctionalList.of(7, 8), FunctionalList.TERMINAL);
		assertEquals(List.of(7, 8), concat.toList());

		concat = FunctionalList.concat(FunctionalList.TERMINAL, FunctionalList.of(7, 8));
		assertEquals(List.of(7, 8), concat.toList());

		concat = FunctionalList.concat(FunctionalList.TERMINAL, FunctionalList.TERMINAL);
		assertEquals(List.of(), concat.toList());
	}

	@Test
	void toListTest() {
		FunctionalList<String> s = FunctionalList.of("x", "y", "z");
		assertEquals(List.of("x", "y", "z"), s.toList());
	}
}