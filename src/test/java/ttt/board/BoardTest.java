package ttt;

import org.junit.Assert;
import org.junit.Test;
import ttt.board.Board;
import ttt.inputOutput.FakeIO;
import ttt.player.FakeComputerPlayer;
import ttt.player.HumanPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.E;
import static ttt.Symbol.X;
import static ttt.Symbol.O;

public class BoardTest {
    Board board = new Board();
    GameSetup expected = new GameSetup();

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private FakeComputerPlayer getFakeComputerMoves(List<Integer> input) {
        return new FakeComputerPlayer(input);
    }

    private Game getGame(FakeIO humanMoves, FakeComputerPlayer computerMoves) {
        HumanPlayer human = new HumanPlayer(humanMoves, board);
        return new Game(board, humanMoves, computerMoves, human);
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }

    public FakeComputerPlayer computerMoves(List<Integer> moves) {
        return getFakeComputerMoves(moves);
    }

    @Test
    public void checksWinFirstRowForCross() {
        Game game = getGame(humanMoves(Arrays.asList("0", "1", "2")), computerMoves(Arrays.asList(3, 6)));
        game.gameLoop();
        Assert.assertEquals(true, board.lineIsWin(X));
    }

    @Test
    public void checksWinLastRowForCross() {
        Game game = getGame(humanMoves(Arrays.asList("1", "5", "2")), computerMoves(Arrays.asList(6, 7, 8)));
        game.gameLoop();
        Assert.assertEquals(true, board.lineIsWin(O));
    }

    @Test
    public void checksWinSecondRowForCross() {
        Game game = getGame(humanMoves(Arrays.asList("3", "4", "5")), computerMoves(Arrays.asList(0, 6)));
        game.gameLoop();
        Assert.assertEquals(true, board.lineIsWin(X));
    }

    @Test
    public void checksNoughtWinsDiagonally() {
        Game game = getGame(humanMoves(Arrays.asList("1", "3", "7")), computerMoves(Arrays.asList(0, 4, 8)));
        game.gameLoop();
        Assert.assertEquals(false, board.lineIsWin(X));
        Assert.assertEquals(true, board.lineIsWin(O));
    }

    @Test
    public void checksAnotherDiagonalWin() {
        Game game = getGame(humanMoves(Arrays.asList("1", "3", "7")), computerMoves(Arrays.asList(0, 4, 8)));
        game.gameLoop();
        Assert.assertEquals(true, board.lineIsWin(O));
    }

    @Test
    public void checksDiagonalWinForCross() {
        Game game = getGame(humanMoves(Arrays.asList("0", "4", "8")), computerMoves(Arrays.asList(1, 6)));
        game.gameLoop();
        Assert.assertEquals(true, board.isWin());
    }

    @Test
    public void checksDiagonalWinForNought() {
        Game game = getGame(humanMoves(Arrays.asList("3", "0", "5")), computerMoves(Arrays.asList(2, 6, 4)));
        game.gameLoop();
        Assert.assertEquals(true, board.isWin());
    }

    @Test
    public void checksVerticalWinMiddleColumnForCross() {
        Game game = getGame(humanMoves(Arrays.asList("1", "4", "7")), computerMoves(Arrays.asList(0, 6)));
        game.gameLoop();
        Assert.assertEquals(true, board.isWin());
    }

    @Test
    public void returnsFalseIfNoWin() {
        Game game = getGame(humanMoves(Arrays.asList("0", "2", "3", "7", "8")), computerMoves(Arrays.asList(1, 4, 5, 6)));
        game.gameLoop();
        Assert.assertEquals(false, board.isWin());
    }

    @Test
    public void checksVerticalWinFirstColumnForCross() {
        Game game = getGame(humanMoves(Arrays.asList("0", "3", "6")), computerMoves(Arrays.asList(1, 8)));
        game.gameLoop();
        Assert.assertEquals(true, board.lineIsWin(X));
        Assert.assertEquals(false, board.lineIsWin(O));
    }

    @Test
    public void checksVerticalWinLastColumn() {
        Game game = getGame(humanMoves(Arrays.asList("2", "1", "5", "3", "8")), computerMoves(Arrays.asList(1, 3)));
        game.gameLoop();
        Assert.assertEquals(true, board.lineIsWin(X));
        Assert.assertEquals(false, board.lineIsWin(O));
    }

    @Test
    public void setsUpThreeByThreeBoard() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, E, E, E, E, E, E, E, E));
        Assert.assertEquals(expectedBoard, board.markPlayer(0, X));
    }

    @Test
    public void returnsCurrentStateOfTheBoard() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, O, E, E, E, E, E, E, E));
        board.markPlayer(1, O);
        Assert.assertEquals(expectedBoard, board.markPlayer(0, X));
    }

    @Test
    public void getsPositionOfCell() {
        Board newBoard = new Board();
        Assert.assertEquals(E, newBoard.get(0));
    }

    @Test(expected = RuntimeException.class)
    public void throwsAnExceptionIfMoveIsNotAvailable() {
        Board newBoard = new Board();
        newBoard.markPlayer(1337, X);
    }

    @Test
    public void largerThanBoardIsInvalid() {
        Board newBoard = new Board();
        Assert.assertFalse(newBoard.isPositionEmpty(37));
    }

    @Test
    public void returnsListOfValidMoves() {
        Board newBoard = new Board();
        List<Integer> validMoves = moves(0, 9);
        Assert.assertEquals(validMoves, newBoard.validMoves());
    }

    @Test
    public void checksBoardFull() {
        Assert.assertTrue(board.notFull());
    }

    @Test
    public void checksBoardNotFull() {
        Board board = new Board();
        Assert.assertTrue(board.notFull());
    }

    private List<Integer> moves(int from, int to) {
        List<Integer> result = new ArrayList<>();
        for (int i = from; i < to; i++) {
            result.add(i);
        }
        return result;
    }
}

