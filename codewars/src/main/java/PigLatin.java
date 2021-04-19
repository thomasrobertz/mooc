import java.util.stream.Stream;

public class PigLatin {
    public static void main(String[] args) {
        //System.out.println(pigIt("O tempora o mores !"));
        System.out.println(pigIt("Pig latin is cool"));
    }
    public static String pigIt(String str) {
        return Stream.of(str.split("\\s+"))
                .map(s -> {
                    if(s.matches("\\w+")) {
                        return s.substring(1, s.length()) + s.substring(0, 1) + "ay";
                    }
                    return s;
                })
                .reduce("", (i, s) -> i + s + " ").trim();
    }
}