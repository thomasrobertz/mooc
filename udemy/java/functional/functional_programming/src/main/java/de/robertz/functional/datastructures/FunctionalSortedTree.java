package de.robertz.functional.datastructures;

import lombok.Getter;

public class FunctionalSortedTree<T extends Comparable<T>> {

	private final T value;
	private final FunctionalSortedTree<T> left;
	private final FunctionalSortedTree<T> right;
	@Getter
	private int length;
	@Getter
	private int height;

	private static final FunctionalSortedTree TERMINAL = new FunctionalSortedTree<>();

	public FunctionalSortedTree() {
		value = null;
		left = TERMINAL;
		right = TERMINAL;
		length = 0;
		height = 0;
	}

	public FunctionalSortedTree(FunctionalSortedTree<T> left, T value, FunctionalSortedTree<T> right) {
		this.left = left;
		this.value = value;
		this.right = right;
		length = 1 + left.length + right.length;
		height = 1 + Math.max(left.height, right.height);
	}

	public static <T extends Comparable<T>> FunctionalSortedTree<T> tree(T ... ts) {
		FunctionalSortedTree<T> tree = TERMINAL;
		for(int i = 0; i < ts.length; i++) {
			tree = tree.insert(ts[i]);
		}
		return tree;
	}

	private FunctionalSortedTree<T> insert(T t) {
		if (isEmpty()) {
			return new FunctionalSortedTree<>(TERMINAL, t, TERMINAL);
		}
		return switch (t.compareTo(value)) {
			case 1 -> new FunctionalSortedTree<>(left, value, right.insert(t));
			case -1 -> new FunctionalSortedTree<>(left.insert(t), value, right);
			default -> new FunctionalSortedTree<>(left, t, right);
		};
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public String toString() {
		return "(%s %s %s)".formatted(left, value, right);
	}
}
