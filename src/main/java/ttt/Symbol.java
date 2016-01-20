package ttt;

public enum Symbol {
    X("X"),
    O("O"),
    E("-");

    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

