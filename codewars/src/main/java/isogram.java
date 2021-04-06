import java.util.*;
import java.util.stream.Collectors;

// https://www.codewars.com/kata/54ba84be607a92aa900000f1
public class isogram {
    public static boolean  isIsogram(String str) {
        Set<Character> encounterdCharacters = new HashSet<>();
        // A bit overkill but I was hellbent on using a lambda.
        // Convert string to lowercase then convert to a character list, then stream over allMatch 
        // to check each cahracter for occurence in the list of already encountered characters. Fails early.
        return str.toLowerCase().chars().mapToObj(c -> (char) c).collect(Collectors.toList()).stream().allMatch(
                s -> {
                    if (encounterdCharacters.contains(s)) {
                        return false;
                    }
                    encounterdCharacters.add(s);
                    return true;
                }
        );
    }
}