package de.robertz.functional.datastructures;

import java.util.Arrays;

import de.robertz.functional.datastructures.Map.Entry;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
	@Test
	public void test() {
		Map<Integer, String> fm = new Map<>(5);
		fm.put(1, "A");
		fm.put(2, "B");
		fm.put(3, "X");
		fm.put(5, "Y");
		fm.put(100, "H");

		assertEquals(5, fm.getSize());
		assertEquals("A", fm.getValue(1));
		assertEquals("Y", fm.getValue(5));
		assertEquals("H", fm.getValue(100));

		assertEquals(fm.getHash(5), fm.getHash(100));
		Entry dual = Arrays.asList(fm.getEntries()).get(0);
		assertEquals(100, dual.getKey());
		assertEquals(5, dual.getNext().getKey());
	}
}