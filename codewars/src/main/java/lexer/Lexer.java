package lexer;

// TODO: Check for possible refactorings
public class Lexer {

    public enum State {
        INITIAL, SUCCESS, COMMENT, ERROR
    }

    private String input;
    private String lexema;
    private int inputCharacterPosition = 0;
    private Lexer.State state = Lexer.State.INITIAL;
    private boolean commentStateOpened = false;

    public Lexer(String input) {
        this.input = input;
    }

    public Lexer next() {
        for (Token token : Token.values()) {

            int matchLength = token.endOfMatch(input);

            if((state == Lexer.State.COMMENT) || commentStateOpened) {
                commentStateOpened = false;
                state = Lexer.State.COMMENT;
                if (matchLength > 0) {
                    if (token == Token.COMMENT_CLOSE) {
                        process(token, matchLength);
                        return this;
                    }
                    advance(matchLength);
                    return this;
                }
            }

            if (matchLength > 0) {
                process(token, matchLength);
                return this;
            }
        }
        state = Lexer.State.ERROR;
        return this;
    }

    private void process(Token token, int matchLength) {
        extract(matchLength);
        transition(token);
        advance(matchLength);
    }

    private void extract(int matchLength) {
        lexema = input.substring(0, matchLength);
    }

    private void advance(int matchLength) {
        input = input.substring(matchLength);
        inputCharacterPosition += matchLength;
    }

    private void transition(Token token) {
        if (state == Lexer.State.COMMENT) {
            if (token == Token.COMMENT_CLOSE) {
                state = Lexer.State.SUCCESS;
            }
            return;
        }
        if (token == Token.COMMENT_OPEN) {
            if (!commentStateOpened) {
                commentStateOpened = true;
                state = Lexer.State.SUCCESS;
                return;
            }
            state = Lexer.State.COMMENT;
            return;
        }
        state = Lexer.State.SUCCESS;
    }

    public String currentLexema() {
        return lexema;
    }

    public int getInputCharacterPosition() {
        return inputCharacterPosition;
    }

    public Lexer.State getState() {
        return state;
    }

    public boolean hasNext() {
        if (state == Lexer.State.ERROR) {
            return false;
        }
        return input.length() > 0;
    }
}
