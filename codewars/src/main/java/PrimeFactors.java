import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// https://www.codewars.com/kata/542f3d5fd002f86efc00081a
public class PrimeFactors {

    public static void main(String[] args) {
        new PrimeFactorization(888).decompose().forEach(System.out::println);
    }

    public static class PrimeFactorization {
        private final Supplier<Long> primeSupplier = new Supplier<Long>() {
            Long nextPrime = 0L;
            @Override
            public Long get() {
                if (nextPrime == 0L) {
                    nextPrime = 2L;
                    return nextPrime;
                }
                nextPrime = Stream.iterate(++nextPrime, i -> ++i)
                        .filter(PrimeFactorization::isPrime)
                        .limit(1).findFirst().get();
                return nextPrime;
            }
        };
        private final long number;
        private final List<Long> primeFactors = new ArrayList<>();
        public PrimeFactorization(long number) {
            if (number <= 1) {
                throw new IllegalArgumentException("Number must be bigger than 1");
            }
            this.number = number;
        }
        public List<Long> decompose() {
            if (isPrime(number)) {
                return List.of(number);
            }
            long prime = primeSupplier.get();
            while(number % prime != 0) {
                prime = primeSupplier.get();
            }
            primeFactors.add((long) prime);
            long divisor = number / prime;
            if(isPrime(divisor)) {
                primeFactors.add(divisor);
                return primeFactors;
            }
            primeFactors.addAll(new PrimeFactorization(divisor).decompose());
            return primeFactors;
        }
        public static boolean isPrime(long number) {
            if (number < 2) {
                return false;
            }
            return IntStream.range(2, (int) Math.sqrt(number) + 1)
                    .filter(n -> number % n == 0)
                    .count() == 0;
        }
    }
}

