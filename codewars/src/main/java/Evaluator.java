import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://www.codewars.com/kata/577e9095d648a15b800000d4
public class Evaluator {

    public static void main(String[] args) {
        System.out.println(evaluate("2 -3 9 4 / + *")); // 10
        //System.out.println(evaluate("25 45 +")); // 70
    }

    public static long evaluate(String s) {
        Lexer lexer = new Lexer(s);
        while(lexer.next());
        return lexer.calc.stack.pop();
    }

    public static class PostfixCalculator {
        public final Stack<Integer> stack = new Stack<>();
        public void push(String lexeme) {
            stack.push(Integer.valueOf(lexeme));
        }
        public void calc(Token token) {
            int rightOperand = stack.pop(); // Subtraction and division are not commutative
            int leftOperand = stack.pop();
            stack.push(
                token.operation.apply(leftOperand, rightOperand));
        }
    }

    public static class Lexer {
        private String input;
        private String lexeme;
        PostfixCalculator calc = new PostfixCalculator();
        public Lexer(String input) {
            this.input = input;
        }
        public boolean next() {
            for (Token token : Token.values()) {
                int matchLength = token.endOfMatch(input);
                if (matchLength > 0) {
                    extract(matchLength);
                    switch(token) {
                        case WHITESPACE:
                            break;
                        case INTEGER:
                            calc.push(lexeme);
                            break;
                        default:
                            calc.calc(token);
                    }
                    advance(matchLength);
                    return hasNext();
                }
            }
            return false;
        }
        private void extract(int matchLength) {
            lexeme = input.substring(0, matchLength);
        }
        private void advance(int matchLength) {
            input = input.substring(matchLength);
        }
        public boolean hasNext() {
            return input.length() > 0;
        }
    }

    public enum Token {
        WHITESPACE("\\s"),
        INTEGER("\\-?\\d+"),
        OP_ADDITION("\\+", Integer::sum),
        OP_SUBTRACTION("\\-", (l, r) -> l - r),
        OP_MULTIPLICATION("\\*", (l, r) -> l * r),
        OP_DIVISION("\\/", (l, r) -> l / r);
        private final Pattern pattern;
        public BinaryOperator<Integer> operation;
        Token(String regex) {
            pattern = Pattern.compile("^" + regex);
        }
        Token(String regex, BinaryOperator<Integer> operation) {
            pattern = Pattern.compile("^" + regex);
            this.operation = operation;
        }
        int endOfMatch(String input) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                return matcher.end();
            }
            return -1;
        }
    }
}
