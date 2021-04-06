import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// My solution to https://www.codewars.com/kata/5541f58a944b85ce6d00006a
public class ProductOfConescutiveFibNumbers {

    public static void main(String[] args) {

        long product = 714;
        //int product = 800;
        //int product = 1870; // 34*55

        FibonacciProductParameters fibonacciProductParameters = find(product);

        long[] x = fibonacciProductParameters.toArray();

    }

    private static FibonacciProductParameters find(long product) {

        long[] seed = new long[] {0, 1};

        List<Long> fibonacci = Stream.iterate(seed,
                n -> n[0] * (n[1] - n[0]) < product,
                n -> new long[] {
                        n[1], n[0] + n[1]
                }).map(n -> n[1]).collect(Collectors.toList());

        List<Long> lastTwoFibonaccis = fibonacci.subList(fibonacci.size() - 2, fibonacci.size());

        return new FibonacciProductParameters(product, lastTwoFibonaccis.get(0), lastTwoFibonaccis.get(1));
    }

    private static class FibonacciProductParameters {
        public long lowerFibonacci;
        public long upperFibonacci;
        public long expectedProduct;
        public long product;
        public FibonacciProductParameters(long expectedProduct, long lowerFibonacci, long upperFibonacci) {
            this.lowerFibonacci = lowerFibonacci;
            this.upperFibonacci = upperFibonacci;
            this.expectedProduct = expectedProduct;
            this.product = lowerFibonacci * upperFibonacci;
        }
        public long isSolution() {
            return (expectedProduct == product) ? 1L : 0L;
        }
        public long[] toArray() {
            return new long[] { lowerFibonacci, upperFibonacci, isSolution() };
        }
    }
}
