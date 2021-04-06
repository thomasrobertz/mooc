import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

// https://www.codewars.com/kata/5552101f47fc5178b1000050
public class DigPow {

    public static long digPow(int n, int p) {
        AtomicInteger sum = new AtomicInteger(0);
        AtomicInteger exponent = new AtomicInteger(p);

        String.valueOf(n).chars().mapToObj(c -> Integer.valueOf(Character.toString(c)))
                .collect(Collectors.toList())
                .forEach(i -> {
                    sum.addAndGet((int) Math.pow((double) i, exponent.get()));
                    exponent.incrementAndGet();
                });

        if (sum.get() % n == 0) {
            return sum.get() / n;
        }

        return -1;
    }

}