package ttt;

public class ComputerPlayer {
    IO io;
    Board board;
    int number;
    Symbol symbol;

    public ComputerPlayer(int number, Board board) {
    this.board = board;
    this.symbol = Symbol.NOUGHT;
    this.number = number;
    }

    public int numberGenerated() {
        return number;
    }

    public void markBoard() {
        board.markPlayer(numberGenerated(), symbol);
    }
}
