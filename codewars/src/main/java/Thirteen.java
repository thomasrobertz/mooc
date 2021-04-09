import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://www.codewars.com/kata/564057bc348c7200bd0000ff
public class Thirteen {
    public static void main(String[] args) {
        long input = 1111111111;
        System.out.println(thirt(input));
    }

    public static long thirt(long n) {

        List<String> numbers = Scanner.toList(String.valueOf(n));
        Stack<String> numberStack = new Stack<String>();
        numberStack.addAll(numbers);

        List<Long> moduloFactorsOf13 = decimalModuloFactorsOf(13, numbers.size());

        Long result = moduloFactorsOf13.stream().reduce(0L, (i, p) ->
            i += Long.parseLong(numberStack.pop()) * ((Number) p).longValue()
        );

        if (result == n) {
            return result;
        }

        return thirt(result);
    }

    public static List<Long> decimalModuloFactorsOf(int number, int size) {
        return Stream.iterate(0, n -> n + 1)
            .map(n -> (long) Math.pow(10, n) % number)
            .limit(size)
            .collect(Collectors.toList());
    }

    public static class Scanner {
        private String input;
        public Scanner(String input) {
            this.input = input;
        }
        public boolean hasNext() {
            return input.length() > 0;
        }
        public String next() {
            String next = input.substring(0, 1);
            input = input.substring(1, input.length());
            return next;
        }
        public static List<String> toList(String input) {
            Scanner scanner = new Scanner(input);
            List<String> result = new ArrayList<>();
            while(scanner.hasNext()) {
                result.add(scanner.next());
            }
            return result;
        }
    }
}
