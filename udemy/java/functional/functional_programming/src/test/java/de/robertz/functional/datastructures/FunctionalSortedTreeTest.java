package de.robertz.functional.datastructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalSortedTreeTest {

	@Test
	void getLength() {
		FunctionalSortedTree fst = FunctionalSortedTree.tree(1, 6, 3, 25, 12);
		System.out.println(	fst.toString());
	}

	@Test
	void getHeight() {
	}
}