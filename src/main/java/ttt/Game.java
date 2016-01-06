package ttt;

public class Game {
    private Board board;
    private HumanPlayer player;
    private Symbol symbolTurn;
    private Symbol cross;
    private Symbol nought;
    private Symbol symbol;
    private Symbol symbolJustPlayed;
    private IO io;
    private ComputerPlayer computerFakeMoves;

    public Game(Board board, IO io, ComputerPlayer computerFakeMoves) {
        cross = Symbol.CROSS;
        nought = Symbol.NOUGHT;
        this.symbol = cross;
        this.board = board;
        this.io = io;
        this.computerFakeMoves = computerFakeMoves;
        this.player = new HumanPlayer(io, board);
    }

    public void gameLoop() {
        initialDisplay();
        while (!board.hasWin() && board.notFull()) {
            io.showOutput("Make your move:");
            markBoard();
            showCurrentBoard();
        }
        endOfGameDisplay();
    }

    public void markBoard() {
        if (board.gameNotOver()) {
            int playerMove = player.move();
            board.markPlayer(playerMove, cross);
            symbolJustPlayed = cross;
            switchPlayerSymbol();
            symbolTurn = nought;
        }

        if (board.gameNotOver()) {
            int computerPlayerMove = computerFakeMoves.move();
            board.markPlayer(computerPlayerMove, nought);
            symbolJustPlayed = nought;
            switchPlayerSymbol();
            symbolTurn = cross;
        }
    }

    public void showCurrentBoard() {
        DisplayBoard currentDisplay = new DisplayBoard(board.getCurrentBoard());
        io.showOutput(currentDisplay.showBoard());
    }

    public Symbol switchPlayerSymbol() {
        symbol = symbol.equals(cross) ? nought : cross;
        return symbol;
    }

    public Symbol getNextSymbol() {
        return symbolTurn;
    }

    public Symbol getPreviousSymbol() {
        return symbolJustPlayed;
    }

    private void endOfGameDisplay() {
        if (board.hasWin()) {
            io.showOutput("Player " + getPreviousSymbol() + " has won!");
        }
        io.showOutput("game over");
    }

    private void initialDisplay() {
        DisplayBoard initialDisplay = new DisplayBoard(board.getCurrentBoard());
        io.showOutput(initialDisplay.showBoard());
        io.showOutput("You are playing as the symbol " + symbol + " make your move:");
    }
}
