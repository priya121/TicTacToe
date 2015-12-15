package ttt;

import java.util.List;

public class DisplayBoard {
    List<String> board;
    String display = "";

    public DisplayBoard(List<String> board) {
        this.board = board;
    }

    public String showBoard() {
        for (int i = 0; i < board.size(); i++) {
            display += " " + board.get(i) + " ";
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
