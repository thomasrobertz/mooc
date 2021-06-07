import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoTest {
    @Test
    void firstMono() {
        Mono.just("A").log().subscribe();
    }

    @Test
    void monoWithConsumer() {
        Mono.just("A").log().subscribe(System.out::println);
    }

    @Test
    void emptyMonoOnComplete() {
        Mono.empty().log().subscribe(null, null, () -> System.out.println("Done."));
    }

    @Test
    void errorExceptionMono() {
        // Next 3 commented out because they would stop execution
        // Throw:
        //Mono.error(new Exception()).log().subscribe();
        // Handle and rethrow:
        //Mono.error(new Exception("ExcMsg")).log().subscribe(System.out::println, e -> System.out.println("Error: " + e.getMessage()));
        // Same as above:
        /*
        Mono.error(new Exception("ExcMsg"))
                .doOnError(e -> System.out.println("Error: " + e.getMessage()))
                .log().subscribe();
         */
        Mono.error(new Exception("ExcMsg"))
                .onErrorResume(e -> {
                    System.out.println("Error: " + e.getMessage());
                    return Mono.just("Error ocurred");
                })
                .log().subscribe();
    }
}
