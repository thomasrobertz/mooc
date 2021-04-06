import java.util.List;
import java.util.Stack;

// https://www.codewars.com/kata/5277c8a221e209d3f6000b56
public class ValidBraces {

    public boolean isValid(String braces) {
        Scanner scanner = new Scanner(braces);
        Engine engine = new Engine();
        boolean result = true;
        while(scanner.hasNext()) {
            result &= engine.next(scanner.next());
        }
        return result && engine.isStackEmtpied();
    }

    public static class Engine {
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
            MatchedPair addPair = createMatchedPair();
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

    public static class MatchedPair {
        private enum Status {
            EMPTY, UNMATCHED
        }
        private List<PairSymbol> pairSymbols;
        private Status status = Status.EMPTY;
        private PairSymbol detectedPair;
        public MatchedPair(PairSymbol ... pairSymbols) {
            this.pairSymbols = List.of(pairSymbols);
        }
        public boolean accept(String symbol) {
            switch(this.status) {
                case EMPTY:
                    PairSymbol pair = findByOpeningSymbol(symbol);
                    if (!(pair instanceof PairSymbol)) {
                        return false;
                    }
                    this.detectedPair = pair;
                    this.status = Status.UNMATCHED;
                    return true;
                case UNMATCHED:
                    if (this.detectedPair.isClosingSymbol(symbol)) {
                        return true;
                    }
            }
            return false;
        }
        private PairSymbol findByOpeningSymbol(String symbol) {
            for(PairSymbol pairSymbol : pairSymbols) {
                if (pairSymbol.isOpeneningSymbol(symbol)) {
                    return pairSymbol;
                }
            }
            return null;
        }
        public Status getStatus() {
            return status;
        }
    }

    public static MatchedPair createMatchedPair() {
        return new MatchedPair(
                new PairSymbol("(", ")"),
                new PairSymbol("[", "]"),
                new PairSymbol("{", "}")
        );
    }

    public static class PairSymbol {
        private String open;
        private String close;
        public PairSymbol(String open, String close) {
            this.open = open;
            this.close = close;
        }
        public boolean isOpeneningSymbol(String symbol) {
            return open.equals(symbol);
        }
        public boolean isClosingSymbol(String symbol) {
            return close.equals(symbol);
        }
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
    }
}