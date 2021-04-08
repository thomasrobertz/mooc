import java.util.function.Function;
import java.util.stream.Stream;

// https://www.codewars.com/kata/517abf86da9663f1d2000003/
public class ConvertToCamelCase {
    public static void main(String[] args) {
        String input = "the-stealth-warrior";
        input = "The_Stealth_Warrior";
        System.out.println(convert(input));
    }

    public static String changeCasing(String input, Function<String, String> caseFunction) {
        return caseFunction.apply(input.substring(0, 1)) + input.substring(1);
    }

    public static String convert(String input) {
        if (input.equals("")) {
            return "";
        }
        boolean wasLower = (input.substring(0, 1).equals(input.substring(0, 1).toLowerCase()));
        String result = Stream.of(input.split("\\-|_")).reduce("",
                (i, p) -> i + changeCasing(p, s -> s.toUpperCase()));
        if (wasLower) {
            result = changeCasing(result, s -> s.toLowerCase());
        }
        return result;
    }
}
