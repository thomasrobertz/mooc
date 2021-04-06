// https://www.codewars.com/kata/5412509bd436bd33920011bc
public class Maskify {
    public static final int numberOfCharactersToPreserve = 4;
    public static final String maskString = "#";
    public static String maskify(String str) {
        int stringLength = str.length();
        if (stringLength < (Maskify.numberOfCharactersToPreserve + 1)) {
            // String is too short, return it.
            return str;
        }
        // Calculate how many characters of the input string to mask.
        int maskLength = stringLength - numberOfCharactersToPreserve;
        // Extract the last n characters from the string that will not be masked.
        String unmaskedString = str.substring(maskLength, stringLength);
        // Build the masked part and add the unmasked part then return it.
        return repeatString(maskString, maskLength) + unmaskedString;
    }
    /** Repeats a given string a specific numer of times.
     */
    public static String repeatString(String input, int repeatTimes) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < repeatTimes; i++) {
            sb.append(input);
        }
        return sb.toString();
    }
}