package ttt.player;
import ttt.Symbol;

public interface Player {
    int move();
    Symbol calculateOwnSymbol();
    Symbol setPlayerSymbol();
}
