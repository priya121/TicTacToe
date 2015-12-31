package ttt;

public class Game {
    private int counter = 0;
    private Board board;
    private DisplayBoard display;
    private Player player;
    private Symbol cross;
    private Symbol nought;
    private IO io;

    public Game(Board board, IO io) {
        cross = Symbol.CROSS;
        nought = Symbol.NOUGHT;
        this.board = board;
        this.io = io;
        this.player = new Player(io, board);
    }

    public void gameLoop() {
        initialDisplay();
        while (!board.hasWin() && board.notFull()) {
            io.showOutput("Player as symbol " + player.getNextSymbol() + " make your move:");
            player.markBoard();
            DisplayBoard currentDisplay = new DisplayBoard(board.getCurrentBoard());
            counter += 1;
            io.showOutput(currentDisplay.showBoard());
        }
        endOfGame();
    }

    public void endOfGame() {
        io.showOutput("Player " + player.getPreviousSymbol() + " has won!");
        io.showOutput("game over");
    }

    public void initialDisplay() {
        DisplayBoard initialDisplay = new DisplayBoard(board.getCurrentBoard());
        io.showOutput(initialDisplay.showBoard());
    }
}
