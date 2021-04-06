package lexer;

public class PairSymbol {
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