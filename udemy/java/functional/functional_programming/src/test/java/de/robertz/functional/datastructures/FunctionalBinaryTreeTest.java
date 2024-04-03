package de.robertz.functional.datastructures;

import java.util.List;

import de.robertz.functional.datastructures.FunctionalBinaryTree.Range;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FunctionalBinaryTreeTest {

	FunctionalBinaryTree<Integer> fst = FunctionalBinaryTree.tree(6, 3, 25, 1, 12);
	FunctionalBinaryTree<Integer> cplx = FunctionalBinaryTree.tree(7, 15, 3, 12, 34, 8, 6, 13, 9, 10, 2, 3);

	@Test
	void getLengthTest() {
		assertEquals(5, fst.getLength());
	}

	@Test
	void getHeightTest() {
		assertEquals(3, fst.getHeight());
		assertEquals(4, fst.insert(99).insert(105).getHeight());
	}

	@Test
	void insertTest() {
		assertEquals(List.of(1, 3, 6, 12, 14, 25), fst.insert(14).toList());
	}

	@Test
	void removeTest() {
		assertEquals(List.of(1, 6, 12, 25), fst.remove(3).toList());
	}

	@Test
	void hasTest() {
		assertTrue(fst.has(6));
		assertFalse(fst.has(19));
	}

	@Test
	void subTest() {
		assertEquals(List.of(8, 9, 10), cplx.sub(8).toList());
		assertEquals(List.of(2), cplx.sub(2).toList());
		assertEquals(List.of(2, 3, 6), cplx.sub(3).toList());
		assertEquals(List.of(6), cplx.sub(6).toList());
		assertEquals(List.of(8, 9, 10, 12, 13, 15, 34), cplx.sub(15).toList());
		assertTrue(cplx.sub(8).has(10));

		assertEquals(List.of(9, 10), cplx.sub(9).toList());
		assertEquals(List.of(8, 9, 10), cplx.sub(8).toList());
		assertEquals(List.of(2), cplx.sub(2).toList());
		assertEquals(List.of(6), cplx.sub(6).toList());
		assertEquals(List.of(2, 3, 6), cplx.sub(3).toList());
	}

	@Test
	void supTest() {
		assertEquals(List.of(8, 9, 10), cplx.sup(9).toList());
		assertEquals(List.of(8, 9, 10, 12, 13), cplx.sup(8).toList());
		assertEquals(List.of(2, 3, 6), cplx.sup(2).toList());
		assertEquals(List.of(2, 3, 6), cplx.sup(6).toList());
		assertEquals(List.of(2, 3, 6, 7, 8, 9, 10, 12, 13, 15, 34), cplx.sup(3).toList());
	}

	@Test
	void localTest() {
		assertEquals(List.of(2, 3, 6), cplx.local(2).toList());
		assertEquals(List.of(2, 3, 6), cplx.local(6).toList());
		assertEquals(List.of(2, 3, 6), cplx.local(3).toList());
		assertEquals(List.of(8, 9, 10), cplx.local(8).toList());
	}

	@Test
	void withinNodeTest() {
		assertTrue(cplx.withinNode(2, 2));
		assertTrue(cplx.withinNode(2, 3));
		assertTrue(cplx.withinNode(2, 6));
		assertTrue(cplx.withinNode(3, 6));
		assertTrue(cplx.withinNode(8, 9));

		assertFalse(cplx.withinNode(2, 8));
		assertFalse(cplx.withinNode(3, 8));
		assertFalse(cplx.withinNode(6, 8));
		assertFalse(cplx.withinNode(8, 10)); // See subTest
		assertFalse(cplx.withinNode(8, 12));
		assertFalse(cplx.withinNode(8, 15));
		assertFalse(cplx.withinNode(8, 34));
	}

	@Test
	void simpleStatsTest() {
		assertEquals(1, fst.min());
		assertEquals(25, fst.max());
		assertEquals(new Range<>(1, 25), fst.range());
	}

	@Test
	void toListTest() {
		assertEquals(List.of(1, 3, 6, 12, 25), fst.toList());
	}

	@Test
	void toStringTest() {
		assertEquals("1 3 6 12 25", fst.toString());
	}
}