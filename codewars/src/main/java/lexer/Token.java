package lexer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Token {
    PARENS_OPEN ("\\("),
    PARENS_CLOSE ("\\)"),
    BRACKET_OPEN ("\\["),
    BRACKET_CLOSE ("\\]"),
    CURLY_OPEN ("\\{"),
    CURLY_CLOSE ("\\}"),
    COMMENT_OPEN ("\\/\\*"),
    COMMENT_CLOSE ("\\*\\/"),
    ALPHANUM_CHARACTER ("[a-z0-9]");

    private final Pattern pattern;

    Token(String regex) {
        pattern = Pattern.compile("^" + regex);
    }

    int endOfMatch(String input) {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.end();
        }
        return -1;
    }
}
