import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// https://www.codewars.com/kata/534a0c100d03ad9772000539
public class PrimeFactorizer {

    public static void main(String[] args) {
        new PrimeFactorizer().factor(72057554846356433L);
        //new PrimeFactorizer().factor(24L);
    }

    public Map<Long, Integer> factor(long n) {
        Map<Long, Integer> result = new HashMap<>();
        new PrimeFactorization(n).decompose().forEach(f -> {
            if(result.containsKey(f)) {
                result.put(f, result.get(f) + 1);
            } else {
                result.put(f, 1);
            }
        });
        return result;
    }

    public static class PrimeFactorization {
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
            long primeCandidate;
            int max = (int) Math.sqrt(number);
            for(primeCandidate = 2; primeCandidate < max; primeCandidate++) {
                if (number % primeCandidate == 0) {
                    if(isPrime(primeCandidate)) {
                        break;
                    }
                }
            }
            primeFactors.add((long) primeCandidate);
            long divisor = number / primeCandidate;
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
            if (number == 2) {
                return true;
            }
            if (number % 2 == 0) {
                return false;
            }
            int max = (int) Math.sqrt(number) + 1;
            for(int i = 3; i < max; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}

