package de.robertz.sec02;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import de.robertz.common.Util;
import reactor.core.publisher.Mono;

import static de.robertz.common.Util.sum;

// IMPORTANT !!!!!
public class MonoFromSupplier05 {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		var l = List.of(5, 6, 7);
		// This works fine
		//Mono.just(sum(l)).subscribe(Util.subscriber());

		// But it isn't lazy. Consider:
		Mono.just(sum(l));
		// The above doesn't have a subscriber (As we know, will not be processed)
		// Important:
		// And yet the sum() operation ist still performed. That's a waste of cpu cycles!
		// As we have said, just() must be evaluated immediately.
		// -> Better, lazy solution:
		Mono<Integer> ms = Mono.fromSupplier(() -> sum(l));
		System.out.println("sum hasnt been called yet...");
		// But will be called now
		ms.subscribe(Util.subscriber("Execute"));

		// Can also use callable (could also use supplier (which can't throw checked exceptions)):
		Mono<String> clb = Mono.fromCallable(() -> {
			return "callable";
		});
		clb.subscribe(System.out::println);

		// Or runnable
		Mono<Void> r = Mono.fromRunnable(() -> System.out.println("Running"));
		r.subscribe();

		// Or Future. Note that CompletableFuture is not lazy by default though!
		// So we can use supplier.
		Mono<String> cf = Mono.fromFuture(CompletableFuture
				.supplyAsync(() -> "CF Supplier"));

		// CompletableFuture runs in a thead pool so make sure to wait a while.
		Util.sleep(1);
		// Future is only executed (lazy) once we subscribe, because of supplier.
		cf.subscribe(System.out::println);
	}
}
