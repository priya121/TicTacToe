package ttt;

public enum Symbol {
    CROSS("X"),
    NOUGHT("O"),
    EMPTY("-");

    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

