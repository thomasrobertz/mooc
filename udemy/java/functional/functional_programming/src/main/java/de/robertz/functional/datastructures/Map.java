package de.robertz.functional.datastructures;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Just a simple Map implementation (Nothing in particular functional)
public class Map<K, V> {

	@Getter private Entry[] entries;
	@Getter	private int size;

	public Map(int size) {
		this.size = size;
		entries = new Entry[size];
		for(int i = 0; i < size; i++) {
			entries[i] = new Entry();
		}
	}

	public Map(Entry[] entries, int size) {
		this.entries = entries;
		this.size = size;
	}

	public Integer getHash(K k) {
		// Used to determine the slot
		return k.hashCode() % size;
	}

	public Map<K, V> put(K k, V v) {
		int hash = getHash(k);
		Entry existingEntry = entries[hash];
		if(isDuplicate(k)) {
			existingEntry.value = v;
		}
		Entry newEntry = new Entry(k, v);
		entries[hash] = newEntry;
		newEntry.next = existingEntry;
		return new Map<>(entries, size);
	}

	private boolean isDuplicate(K k) {
		boolean result = false;
		Entry entry = entries[getHash(k)];
		while(Objects.nonNull(entry)) {
			if (k.equals(entry.key)) {
				result = true;
			}
			entry = entry.next;
		}
		return result;
	}

	public V getValue(K k) {
		V v = null;
		int hash  = getHash(k);
		Entry entry = entries[hash];

		while(Objects.nonNull(entry.next)) {
			if(k.equals(entry.getKey())) {
				v = (V) entry.getValue();
				break;
			}
			entry = entry.next;
		}

		return v;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Entry {
		private Object key;
		private Object value;
		private Entry next;

		public Entry(Object key, Object value) {
			this.key = key;
			this.value = value;
		}
	}
}
