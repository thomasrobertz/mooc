package de.robertz.functional.collectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

public class Collectors {
	public static void main(String[] args) {

		List<Integer> i = List.of(1, 2, 3, 4, 5, 6, 7);

		/*
		* 1. We are typing the Collector:
		*
		* 	Collector<T, A, R>
		*
		* where
		* <T> is the input type
		* <A> the accumulation type
		* <R> is the return type
		* */
		Collector<Integer, List<Integer>, List<Integer>> toListCollector =
				Collector.of(
					() -> new ArrayList<>(), 							// The Supplier
					(list, element) -> list.add(element), // The Accumulator, a BiConsumer
					(leftList, rightList) -> {						// The Combiner, BinaryOperator (Returns the same types as the generic parameters)
						leftList.addAll(rightList);
						return leftList;
					},
					Characteristics.IDENTITY_FINISH				// The Characteristics. IDENTITY_FINISH is the most common and means:
																								// 	-No need to execute finisher (The results of accumulator need not further processing)
																								// 	-The result of combiner need no further processing.
		);

		// 2. Now we can collect to a return type <R>
		List<Integer> evens = i.stream()
				.filter(x -> x % 2 == 0) // example transformation, we would not need it and would then get the whole list.
				.collect(toListCollector);

		System.out.println("Evens:");
		evens.forEach(System.out::println);

		finisherExample();
	}

	private static void finisherExample() {

		List<Integer> i = List.of(1, 2, 3, 4, 5, 6, 7, 9, 10);

		Collector<Integer, List<Integer>, List<Integer>> shufflingFinisherCollector =
				Collector.of(
						ArrayList::new, // Suppplier, accumulator and combiner same as above toListCollector example
						List::add,
						(leftList, rightList) -> {
							leftList.addAll(rightList);
							return leftList;
						},
						(list) -> {			// The Finisher, a Function.
							Collections.shuffle(list);	// Normally we may not want to shuffle using a finisher, it's just for demo purposes.
							return list;
						},
						Characteristics.UNORDERED	// We want our own order (which is no order)
				);

		System.out.println("Shuffled:");
		i.stream().collect(shufflingFinisherCollector).forEach(System.out::println);
	}
}
