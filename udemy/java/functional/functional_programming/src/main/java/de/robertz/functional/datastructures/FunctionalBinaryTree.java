package de.robertz.functional.datastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;

/*
 * FunctionalBinaryTree has some very intuitive recursive methods.
 */
public class FunctionalBinaryTree<T extends Comparable<T>> {

	private final T value;
	private final FunctionalBinaryTree<T> left;
	private final FunctionalBinaryTree<T> right;
	@Getter
	private int length;
	@Getter
	private int height;

	private static FunctionalBinaryTree TERMINAL = new FunctionalBinaryTree();

	public FunctionalBinaryTree() {
		value = null;
		left = TERMINAL;
		right = TERMINAL;
		length = 0;
		height = 0;
	}

	public FunctionalBinaryTree(FunctionalBinaryTree<T> left, T value, FunctionalBinaryTree<T> right) {
		this.left = left;
		this.value = value;
		this.right = right;
		length = 1 + left.length + right.length;
		height = 1 + Math.max(left.height, right.height);
	}

	public static <T extends Comparable<T>> FunctionalBinaryTree<T> tree(T ... ts) {
		FunctionalBinaryTree<T> tree = TERMINAL;
		for (T t : ts) {
			tree = tree.insert(t);
		}
		return tree;
	}

	public FunctionalBinaryTree<T> insert(T t) {
		if (isEmpty()) {
			return new FunctionalBinaryTree<T>(TERMINAL, t, TERMINAL);
		}
		return switch (t.compareTo(value)) {
			case 1 -> new FunctionalBinaryTree<>(left, value, right.insert(t));
			case -1 -> new FunctionalBinaryTree<>(left.insert(t), value, right);
			default -> new FunctionalBinaryTree<>(left, t, right);
		};
	}

	public FunctionalBinaryTree<T> remove(T t) {
		if (t.compareTo(value) < 0) {
			return new FunctionalBinaryTree<>(left.remove(t), value, right);
		}
		if (t.compareTo(value) > 0) {
			return new FunctionalBinaryTree<>(left, value, right.remove(t));
		}
		return left.join(right);
	}

	public boolean has(T t) {
		if (Objects.isNull(value)) {
			return false;
		}
		if (t.compareTo(value) < 0) {
			return left.has(t);
		}
		if (t.compareTo(value) > 0) {
			return right.has(t);
		}
		return value.equals(t);
	}

	/*
	 * Returns true if both values are within the same node
	 */
	public boolean neighbours(T first, T second) {
		if (first.equals(second)) {
			return true;
		}

		FunctionalBinaryTree<T> lf = local(first);
		FunctionalBinaryTree<T> ls = local(second);

		return local(first).localEqualsByValue(local(second));
	}

	/*
	 * Return the whole subtree that contains (=super tree) the given value.
	 */
	public FunctionalBinaryTree<T> sup(T t) {
		return perfomSup(t, new FunctionalBinaryTree<>(left, value, right));
	}

	/*
	 * Return the whole subtree of the given value.
	 */
	public FunctionalBinaryTree<T> sub(T t) {
		if (Objects.isNull(value)) {
			return null;
		}
		if (t.compareTo(value) < 0) {
			return left.sub(t);
		}
		if(localContains(t)) {
			return new FunctionalBinaryTree<>(left, value, right);
		}
		if (t.compareTo(value) > 0) {
			return right.sub(t);
		}
		return null;
	}

	/*
	 * Return just the subtree that contains the given value.
	 */
	public FunctionalBinaryTree<T> local(T t) {
		return perfomLocal(t, new FunctionalBinaryTree<>(left, value, right));
	}

	public T min() {
		// The lowest value will be stored at the leftmost place.
		if (left.equals(TERMINAL)) {
			return value;
		}
		return left.min();
	}

	public T max() {
		// The highest value will be stored at the rightmost place.
		if (right.equals(TERMINAL)) {
			return value;
		}
		return right.max();
	}

	public Range<T> range() {
		return new Range<>(min(), max());
	}

	public List<T> toList() {
		List<T> resultingList = new ArrayList<>();
		if (Objects.nonNull(left)) {
			resultingList.addAll(left.toList());
		}
		if (Objects.nonNull(value)) {
			resultingList.add(value);
		}
		if (Objects.nonNull(right)) {
			resultingList.addAll(right.toList());
		}
		return resultingList;
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public String toString() {
		if (Objects.isNull(value)) {
			return "";
		}
		return "%s %s %s".formatted(left, value, right).replaceAll("\\s{2,}", " ").trim();
	}

	/*
	 * Returns true if the given value is equal this node's value, or in other case,
	 * if it is equal to this node's left or right value.
	 */
	private boolean localContains(T t) {
		if (Objects.nonNull(value)) {
			return value.equals(t);
		}
		return siblings(t);
	}

	private boolean siblings(T t) {
		boolean leftSibling = false;
		boolean rightSibling = false;
		if (Objects.nonNull(left.value)) {
			leftSibling = left.value.equals(t);
		}
		if (Objects.nonNull(right.value)) {
			rightSibling = right.value.equals(t);
		}
		return leftSibling || rightSibling;
	}

	/*
	 * Merge the tree at a node, effectively eliminating the node's value
	 */
	private FunctionalBinaryTree<T> join(FunctionalBinaryTree<T> other) {
		if (isEmpty()) {
			return other;
		}
		if (other.isEmpty()) {
			return this;
		}
		return new FunctionalBinaryTree<>(left.join(right), value, other);
	}

	private FunctionalBinaryTree<T> perfomSup(T t, FunctionalBinaryTree<T> top) {
		if (Objects.isNull(value)) {
			return null;
		}
		if (localContains(t)) {
			return top;
		}
		if (t.compareTo(value) < 0) {
			return left.perfomSup(t, this);
		}
		if (t.compareTo(value) > 0) {
			return right.perfomSup(t, this);
		}
		return null;
	}

	private FunctionalBinaryTree<T> perfomLocal(T t, FunctionalBinaryTree<T> top) {

		if (Objects.isNull(value)) {
			return null;
		}
		if (t == value) {
			return new FunctionalBinaryTree<>(left, value, right);
		}
		else {
			// Length comparison is a bit of a hack but seems to work. TODO: Is there a better way?
			if (t.equals(left.value)) {
				if (length <= 3) {
					return new FunctionalBinaryTree<>(top.left, value, top.right);
				}
				if (left.length <= 3) {
					return left;
				}
			}
			if (t.equals(right.value)) {
				if (length <= 3) {
					return new FunctionalBinaryTree<>(top.left, value, top.right);
				}
				if (right.length <= 3) {
					return right;
				}
			}
		}
		if (t.compareTo(value) < 0) {
			return left.perfomLocal(t, left);
		}
		if (t.compareTo(value) > 0) {
			return right.perfomLocal(t, right);
		}
		return null;
	}

	private boolean localEqualsByValue(FunctionalBinaryTree<T> other) {

		boolean thisHasLeftValue = Objects.nonNull(this.left.value);
		boolean thisHasRightValue = Objects.nonNull(this.right.value);
		boolean otherHasLeftValue = Objects.nonNull(other.left.value);
		boolean otherHasRightValue = Objects.nonNull(other.right.value);
		boolean equalsByLeftValue = false;
		boolean equalsByRightValue = false;

		if (thisHasLeftValue == otherHasLeftValue) {
			equalsByLeftValue = true;
			if (Objects.nonNull(left.value)) {
				equalsByLeftValue = left.value.equals(other.left.value);
			}
		}

		if (thisHasRightValue == otherHasRightValue) {
			equalsByRightValue = true;
			if (Objects.nonNull(right.value)) {
				equalsByRightValue = right.value.equals(other.right.value);
			}
		}

		return value.equals(other.value) && equalsByLeftValue && equalsByRightValue;
	}

	record Range<T>(T low, T high) { }
}
