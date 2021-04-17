import java.util.*;
import java.util.stream.Collectors;
import static java.util.Comparator.comparing;

// https://www.codewars.com/kata/51e056fe544cf36c410000fb/train/java
public class TopWords {

    public static void main(String[] args) {
        String text = "e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e";
        top3(text).forEach(System.out::println);
    }

    public static List<String> top3(String s) {
        Map<String, Integer> top3Words = new HashMap<String, Integer>();
        List.of(clean(s).split("\\s")).stream()
                .filter(t -> t.length() > 0).forEach(tf -> {
                    if(top3Words.containsKey(tf)) {
                        top3Words.put(tf, top3Words.get(tf) + 1);
                    } else {
                        top3Words.put(tf, 1);
                    }
        });

        List<String> res = top3Words.entrySet().stream()
                .sorted(comparing(Map.Entry<String, Integer>::getValue).reversed()
                        .thenComparing(Map.Entry::getKey)).limit(10)
                .map(tf -> tf.getKey().toLowerCase()).collect(Collectors.toList());

        return top3Words.entrySet().stream()
                .sorted(comparing(Map.Entry<String, Integer>::getValue).reversed()
                        .thenComparing(Map.Entry::getKey)).limit(3)
                .map(tf -> tf.getKey().toLowerCase()).collect(Collectors.toList());
    }

    public static String clean(String input) {
        return input.replaceAll("/+", " ")
                .replaceAll("\\s[2,]", " ")
                .replaceAll(",", " ")
                .replaceAll(";", " ")
                .replaceAll("-", " ")
                .replaceAll("_", " ")
                .replaceAll("\\?", " ")
                .replaceAll("!", " ")
                .replaceAll("\\.", " ")
                .replaceAll(":", " ")
                .replaceAll("\\s'+\\s?", "").trim();
    }
}
