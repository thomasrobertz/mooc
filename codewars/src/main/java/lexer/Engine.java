package lexer;

import java.util.Stack;

public class Engine {
    private Stack<MatchedPair> matchedPairs = new Stack<>();
    public boolean next(String symbol) {
        if (matchedPairs.empty()) {
            return addPair(symbol);
        }
        if (matchedPairs.peek().accept(symbol)) {
            matchedPairs.pop();
            return true;
        }
        return addPair(symbol);
    }
    private boolean addPair(String symbol) {
        MatchedPair addPair = MatchedPair.createMatchedPair();
        if (addPair.accept(symbol)) {
            matchedPairs.push(addPair);
            return true;
        }
        return false;
    }
    public boolean isStackEmtpied() {
        return matchedPairs.empty();
    }
}
