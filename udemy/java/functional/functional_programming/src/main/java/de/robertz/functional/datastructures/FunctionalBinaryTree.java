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
		for(int i = 0; i < ts.length; i++) {
			tree = tree.insert(ts[i]);
		}
		return tree;
	}

	public FunctionalBinaryTree<T> insert(T t) {
		if (isEmpty()) {
			return new FunctionalBinaryTree<>(TERMINAL, t, TERMINAL);
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
		return left.merge(right);
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

	public boolean withinBranch(T first, T second) {
		return true;
	}

	public boolean withinNode(T first, T second) {
		if (first.equals(second)) {
			return true;
		}
		FunctionalBinaryTree<T> firstSubTree = sub(first);
		FunctionalBinaryTree<T> secondSubTree = sub(second);

		if (firstSubTree.equals(secondSubTree)) {
			return true;
		}

//		boolean foundInFirst =  firstSubTree.value.equals(second)
//			|| firstSubTree.left.value.equals(second)
//			|| firstSubTree.right.value.equals(second);
//		boolean foundInSecond =  secondSubTree.value.equals(first)
//				|| secondSubTree.left.value.equals(first)
//				|| secondSubTree.right.value.equals(first);

		boolean foundInFirst = valueIn(firstSubTree, second);
		boolean foundInSecond = valueIn(secondSubTree, first);

		return foundInFirst || foundInSecond;
	}

	private boolean valueIn(FunctionalBinaryTree<T> tree, T value) {

		if (Objects.nonNull(tree.value)) {
			if (tree.value.equals(value)) {
				return true;
			}
		}

		boolean valueInLeftNode = false;
		boolean valueInRightNode = false;

		if (Objects.nonNull(tree.left.value)) {
			valueInLeftNode = tree.left.value.equals(value);
		}

		if (Objects.nonNull(tree.right.value)) {
			valueInRightNode = tree.right.value.equals(value);
		}

		return valueInLeftNode || valueInRightNode;
	}

	public FunctionalBinaryTree<T> sup(T t) {
		return perfomSup(t, new FunctionalBinaryTree<>(left, value, right));
	}

	private FunctionalBinaryTree<T> perfomSup(T t, FunctionalBinaryTree<T> top) {
		if (Objects.isNull(value)) {
			return null;
		}
		if (localContainsStrict(t)) {
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

	public FunctionalBinaryTree<T> sub(T t) {
		if (Objects.isNull(value)) {
			return null;
		}
		if (t.compareTo(value) < 0) {
			return left.sub(t);
		}
		if(localContainsStrict(t)) {
			return new FunctionalBinaryTree<>(left, value, right);
		}
		if (t.compareTo(value) > 0) {
			return right.sub(t);
		}
		return null;
	}

	public FunctionalBinaryTree<T> local(T t) {
		return perfomLocal(t, new FunctionalBinaryTree<>(left, value, right));
	}

	private FunctionalBinaryTree<T> perfomLocal(T t, FunctionalBinaryTree<T> top) {

		boolean byLeft = false;
		boolean byValue = false;

		if(Objects.nonNull(left.value)) {
			if (t.equals(left.value)) {
				byLeft = true;
			}
		}
		if(Objects.nonNull(value)) {
			if (t.equals(value)) {
				byValue = true;
			}
		}

		/*
		if(Objects.nonNull(left.value)) {
			if (t.equals(left.value)) {
				return top.left;
			}
		}
		if(Objects.nonNull(value)) {
			if (t.equals(value)) {
				return new FunctionalBinaryTree<>(left, value, right);
			}
		}
		if(Objects.nonNull(right.value)) {
			if (t.equals(right.value)) {
				return top.right;
			}
		}
		*/

		if (t.compareTo(value) < 0) {
			return left.perfomLocal(t, top.left);
		}
		return right.perfomLocal(t, top.right);

	}

//	private FunctionalBinaryTree<T> perfomLocal(T t, FunctionalBinaryTree<T> top) {
//		if (Objects.isNull(value)) {
//			return null;
//		}
//		if (Objects.nonNull(left.value)) {
//			if (t.equals(left.value)) {
//				return top.left;
//			}
//		}
//		if (t.equals(value)) {
//			return new FunctionalBinaryTree<>(left, value, right);
//		}
//		if (Objects.nonNull(right.value)) {
//			if (t.equals(right.value)) {
//				return top.right;
//			}
//		}
//		if (t.compareTo(value) < 0) {
//			return left.perfomLocal(t, top.left);
//		}
//		return right.perfomLocal(t, top.right);
//	}

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

	private boolean localContainsStrict(T t) {
		if (Objects.nonNull(value)) {
			return value.equals(t);
		}
		return siblings(t);
	}

	private boolean localContainsLenient(T t) {
		boolean valueEquals = false;
		if (Objects.nonNull(value)) {
			valueEquals = value.equals(t);
		}
		return valueEquals || siblings(t);
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

	private FunctionalBinaryTree<T> merge(FunctionalBinaryTree<T> other) {
		if (isEmpty()) {
			return other;
		}
		if (other.isEmpty()) {
			return this;
		}
		return new FunctionalBinaryTree<>(left.merge(right), value, other);
	}

	record Range<T>(T low, T high) { }
}
