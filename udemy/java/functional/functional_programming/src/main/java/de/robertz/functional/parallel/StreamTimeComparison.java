package de.robertz.functional.parallel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import lombok.SneakyThrows;

/* In this example single thread parallelStream is faster than FJP.
Things to try:
Increase Task Complexity: Make the expensiveFilterFunction more computationally intensive.
	This could involve more complex mathematical calculations or processing that simulates real-world scenarios.
Scale the Dataset: Gradually increase the size of your dataset (the list of Employee objects)
	to see how the performance scales with data volume. There might be a threshold beyond which the custom ForkJoinPool begins to show advantages, especially if it's configured with an optimal level of parallelism for your hardware.
Adjust Parallelism Levels: Experiment with different parallelism levels for the custom ForkJoinPool.
	The optimal number of threads often depends on the number of CPU cores available and the nature of the tasks. Finding the right balance can significantly impact performance.
* */
public class StreamTimeComparison {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		Map<String, Long> times = new HashMap<>();

		List<Employee> emps = new ArrayList<>();
		List<Employee> empsWU = new ArrayList<>();
		for(int n = 0; n < 10000; n++) {
			emps.add(new Employee("John", 2 * n));
		}
		for(int n = 0; n < 2000; n++) {
			empsWU.add(new Employee("John", 2 * n));
		}

		// Parallel Stream
		// Warm up
		empsWU.parallelStream()
				.filter(StreamTimeComparison::expensiveFilterFunction).count();

		long start = System.currentTimeMillis();
		emps.parallelStream()
						.filter(StreamTimeComparison::expensiveFilterFunction).count();
		long end = System.currentTimeMillis();
		times.put("Parrallel", end - start);

		// Parallel Stream in thread pool
		// Warm up
		fjp.submit(() -> empsWU.parallelStream()
						.filter(StreamTimeComparison::expensiveFilterFunction).count())
				.get();

		start = System.currentTimeMillis();
		fjp.submit(() -> emps.parallelStream()
						.filter(StreamTimeComparison::expensiveFilterFunction).count())
				.get(); // Block since we want the execution time
		end = System.currentTimeMillis();
		times.put("Parrallel FJP", end - start);

		times.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
					(oldValue, newValue) -> oldValue, LinkedHashMap::new))
				.forEach((k ,v) -> System.out.println(k + " " + v));
	}

	@SneakyThrows
	static boolean expensiveFilterFunction(Employee e) {
		Thread.sleep(1);
		return e.salary > 250;
	}

	record Employee(String name, int salary) { }
}
