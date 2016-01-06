package ttt.board;

import ttt.Symbol;

import java.util.List;

public class DisplayBoard {
    List<Symbol> board;
    String display = "";

    public DisplayBoard(List<Symbol> board) {
        this.board = board;
    }

    public String showBoard() {
        for (int i = 0; i < board.size(); i++) {
            display += " " + board.get(i).getSymbol() + " ";
            if ((i + 1) % 3 == 0) {
                display += "\n";
            }
        }
        return display;
    }

    public void clearScreen() {
        String clear = "\033[2J\033[1;1H";
        System.out.println(clear);
        System.out.flush();
    }
}
