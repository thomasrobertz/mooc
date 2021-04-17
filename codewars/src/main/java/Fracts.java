import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://www.codewars.com/kata/54d7660d2daf68c619000d95
public class Fracts {

    public static void main(String[] args) {
        try {
            test_fractions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertFrac(long[][] lst) {

        List<Long[]> fractions = Stream.of(lst)
                .map(f -> reduceFraction(f[0], f[1]))
                .collect(Collectors.toList());
        Long largest = 0L;

        for(Long[] f : fractions) {
            if (f[1] > largest) {
                largest = f[1];
            }
        }

        boolean allDivisible;
        int factor = 1;
        Long lcd;

        do {
            lcd = largest * factor;
            allDivisible = (fractions.stream()
                    .map(f -> f[1]).reduce(lcd, (i, f) -> i += i % f) - lcd) == 0;
            factor++;
        } while(!allDivisible);

        StringBuilder sb = new StringBuilder();
        final Long leastCommonDenominator = lcd;

        fractions.stream().forEach(f -> {
            Long[] reduced = reduceFraction(f[0], f[1]);
            sb.append(String.format("(%d,%d)", reduced[0] * (leastCommonDenominator / reduced[1]), leastCommonDenominator));
        });

        return sb.toString();
    }

    public static Long[] reduceFraction(long numerator, long denominator) {
        for(long commonDivisor = numerator; commonDivisor > 1; commonDivisor--) {
            if((numerator % commonDivisor == 0) && (denominator % commonDivisor == 0)) {
                return new Long[] { numerator / commonDivisor, denominator / commonDivisor };
            }
        }
        return new Long[] { numerator, denominator };
    }

    public static void test_fractions() throws Exception {
        long[][] lst;
        lst = new long[][] { {1, 2}, {1, 3}, {10, 40} };
        //lst = new long[][] { {69, 130}, {87, 1310}, {30, 40} };

        /*
        Expect (18078,34060)(2262,34060)(25545,34060)

        Actual (20,60)(15,60)(15,60)(12,60)(12,60)
        Expect (15,60)(15,60)(12,60)(20,60)(12,60)
         */

        //lst = new long[][] { {1, 2}, {1, 3}, {10, 40} };
        if(!("(6,12)(4,12)(3,12)".equals(Fracts.convertFrac(lst)))) {
            throw new RuntimeException("Wrong");
        }
    }
}
