package lexer;

import java.util.List;

public class MatchedPair {
    private enum Status {
        EMPTY, UNMATCHED
    }
    private List<PairSymbol> pairSymbols;
    private MatchedPair.Status status = MatchedPair.Status.EMPTY;
    private PairSymbol detectedPair;
    public MatchedPair(PairSymbol... pairSymbols) {
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
                this.status = MatchedPair.Status.UNMATCHED;
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
    public MatchedPair.Status getStatus() {
        return status;
    }
    public static MatchedPair createMatchedPair() {
        return new MatchedPair(
                new PairSymbol("(", ")"),
                new PairSymbol("[", "]"),
                new PairSymbol("{", "}"),
                new PairSymbol("/*", "*/")
        );
    }
}
