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

        List<Long[]> preparedFractions = Stream.of(lst).map(f -> reduceFraction(f[0], f[1])).sorted(Comparator.comparing(f -> f[1])).collect(Collectors.toList());
        Collections.reverse(preparedFractions);
        Long pivot = preparedFractions.get(0)[1];

        boolean allDivisible;
        int factor = 1;
        Long lcd;

        do {
            lcd = pivot * factor;
            allDivisible = (preparedFractions.stream().map(f -> f[1]).reduce(lcd, (i, f) -> i += i % f)) - lcd == 0;

            /*
            allDivisible = true;

            for(Long[] fract : preparedFractions) {
                allDivisible = allDivisible && (fract[1] % lcd == 0);
            }
            */

            factor++;
        } while(!allDivisible);

        StringBuilder sb = new StringBuilder();
        final Long leastCommonDenominator = lcd;
        Collections.reverse(preparedFractions);

        Long[] x = reduceFraction(51090,68120);

        preparedFractions.stream().forEach(f -> {
            final Long[] reduced = reduceFraction(f[0], f[1]);
            //sb.append(String.format("(%d,%d)", f[0] * (leastCommonDenominator / f[1]), leastCommonDenominator));
            sb.append(String.format("(%d,%d)", reduced[0] * (leastCommonDenominator / reduced[1]), leastCommonDenominator));
        });


        System.out.println(sb.toString());

        return sb.toString();
    }

    public static Long[] reduceFraction(long numerator, long denominator) {
        if (denominator % numerator == 0) {
            return new Long[] { 1L, denominator / numerator };
        }
        return new Long[] { numerator, denominator };
    }

    public static void test_fractions() throws Exception {
        long[][] lst;
        lst = new long[][] { {1, 2}, {1, 3}, {1, 4} };
        lst = new long[][] { {69, 130}, {87, 1310}, {30, 40} };

        /*

        Actual (51090,68120)(36156,68120)(4524,68120)
        Expect (18078,34060)(2262,34060)(25545,34060)

        Actual (20,60)(15,60)(15,60)(12,60)(12,60)
        Expect (15,60)(15,60)(12,60)(20,60)(12,60)
         */

        //lst = new long[][] { {1, 2}, {1, 3}, {10, 40} };
        if(!("(6,12)(4,12)(3,12)".equals(Fracts.convertFrac(lst)))) {
            throw new RuntimeException("Wrong");
        }
    }

    // convertFracs [(1, 2), (1, 3), (1, 4)] `shouldBe` [(6, 12), (4, 12), (3, 12)]
}
