package ttt.board;

import org.junit.Assert;
import org.junit.Test;
import ttt.Game;
import ttt.GameCreator;
import ttt.SetupBoard;
import ttt.Symbol;
import ttt.inputOutput.FakeIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.*;

public class BoardTest {
    Board threeByThreeBoard = new Board(3);
    Board fourByFourBoard = new Board(4);
    SetupBoard expected = new SetupBoard();

    @Test
    public void returnsEmptyThreeByThreeBoard() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(E, E, E,
                                                                         E, E, E,
                                                                         E, E, E));
        Assert.assertEquals(expectedBoard, threeByThreeBoard.contentsOfBoard());
    }

    @Test
    public void returnsEmptyFourByFour() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(E, E, E, E,
                                                                         E, E, E, E,
                                                                         E, E, E, E,
                                                                         E, E, E, E));
        Assert.assertEquals(expectedBoard, fourByFourBoard.contentsOfBoard());
    }

    @Test
    public void returnsEmptyTenByTenBoard() {
        Board tenByTen = new Board(10);
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E));
        Assert.assertEquals(expectedBoard, tenByTen.contentsOfBoard());
    }

    @Test
    public void firstRowOnFourByFourIsAWinForCross() {
        Board fourByFour = new Board(Arrays.asList(X, X, X, X,
                                                   E, E, E, E,
                                                   E, E, E, E,
                                                   E, E, E, E));
        Assert.assertTrue(fourByFour.checkWins(X));
        Assert.assertEquals(false, fourByFour.checkWins(O));
    }

    @Test
    public void secondRowOnFourByFourIsNotAWinForCross() {
        Board newFourByFour = new Board(Arrays.asList(E, E, E, E,
                                                      X, X, X, E,
                                                      E, E, E, E,
                                                      E, E, E, E));
        Assert.assertFalse(newFourByFour.checkWins(X));
    }

    @Test
    public void findsThirdRowOnFourByFourIsAWinforCross() {
        Board newFourByFour = new Board(Arrays.asList(E, E, E, E,
                                                      E, E, E, E,
                                                      X, X, X, X,
                                                      E, E, E, E));
        Assert.assertTrue(newFourByFour.checkWins(X));
    }

    @Test
    public void findsFourthRowOnFourByFourIsAWinForNought() {
        Board newFourByFour = new Board(Arrays.asList(E, E, E, E,
                                                      E, E, E, E,
                                                      E, E, E, E,
                                                      O, O, O, O));
        Assert.assertTrue(newFourByFour.checkWins(O));
        Assert.assertFalse(newFourByFour.checkWins(X));
    }

    @Test
    public void checksFirstColumnForWinForCross() {
        Board fourByFour = new Board(Arrays.asList(X, E, E, E,
                                                   X, E, E, E,
                                                   X, E, E, E,
                                                   E, E, E, E));
        Assert.assertFalse(fourByFour.checkWins(X));
    }

    @Test
    public void checksThirdColumnForWin() {
        Board fourByFour = new Board(Arrays.asList(E, E, X, E,
                                                   E, E, X, E,
                                                   E, E, X, E,
                                                   E, E, X, E));
        Assert.assertTrue(fourByFour.checkWins(X));
    }

    @Test
    public void checksDiagonalFromRightToLeftForWin() {
        Board board = new Board(Arrays.asList(X, E, E, E,
                                              E, X, E, E,
                                              E, E, X, E,
                                              E, E, E, X));
        Assert.assertTrue(board.checkWins(X));
    }

    @Test
    public void findsFirstRowOnThreeByThreeIsAWin() {
        Board threeByThree = new Board(Arrays.asList(X, X, X,
                                                     E, E, E,
                                                     O, O, O));
        Assert.assertTrue(threeByThree.checkWins(O));
    }

    @Test
    public void checksWinForEitherPlayerOnFourByFour() {
        Board fourByFour = new Board(Arrays.asList(X, X, X, X,
                                                   E, E, O, E,
                                                   E, O, X, E,
                                                   E, O, X, E));
        Assert.assertEquals(true, fourByFour.isWin());
    }

    @Test
    public void checksWinForEitherPlayerOnTenByTen() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         E, E, E, E, E, E, E, E, E, E,
                                                                         O, O, O, O, O, O, O, O, O, O,
                                                                         E, E, E, E, E, E, E, E, E, E));
        Board tenByTen = new Board(expectedBoard);
        Assert.assertEquals(expectedBoard, tenByTen.contentsOfBoard());
        Assert.assertTrue(tenByTen.checkWins(O));
        Assert.assertEquals(true, tenByTen.checkWins(O));
    }

    @Test
    public void checksWinForThreeByThree() {
        Board threeByThree = new Board(Arrays.asList(X, O, X,
                                                     E, O, E,
                                                     E, O, X));
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksWinOnAThreeByThreeBoard() {
        Board threeByThree = new Board(Arrays.asList(O, X, X,
                                                     E, O, E,
                                                     O, X, O));
        Assert.assertEquals(true, threeByThree.checkWins(O));
        Assert.assertEquals(false, threeByThree.checkWins(X));
    }

    @Test
    public void checksWinFirstRowForCross() {
        Board threeByThree = new Board(Arrays.asList(X, X, X,
                                                     O, E, O,
                                                     E, E, O));
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksWinLastRowForCross() {
        FakeIO userInput = new FakeIO(Arrays.asList("3", "1", "1", "6", "5", "7", "2", "8", "N"));
        Game game = getThreeByThreeGame(userInput);
        game.gameLoop();
        Assert.assertEquals(true, game.board.isWin());
    }

    @Test
    public void checksWinSecondRowForCross() {
        FakeIO userInput = new FakeIO(Arrays.asList("3", "1", "3", "0", "4", "6", "5", "8", "N"));
        Game game = getThreeByThreeGame(userInput);
        game.gameLoop();
        Assert.assertEquals(true, game.board.isWin());
    }

    @Test
    public void checksWinForFourByFourBoard() {
        FakeIO userInput = new FakeIO(Arrays.asList("4", "1", "4", "0", "5", "15", "6", "8", "7", "N"));
        Game game = getFourByFourGame(userInput);
        game.gameLoop();
        Assert.assertEquals(true, game.board.isWin());
    }

    @Test
    public void checksNoughtWinsDiagonally() {
        FakeIO userInput = new FakeIO(Arrays.asList("3", "1", "1", "0", "3", "4", "7", "8", "N"));
        Game game = getThreeByThreeGame(userInput);
        game.gameLoop();
        Assert.assertEquals(true, game.board.isWin());
    }

    @Test
    public void checksAnotherDiagonalWin() {
        FakeIO userInput = new FakeIO(Arrays.asList("3", "1", "1", "0", "3", "4", "7", "8", "N"));
        Game game = getThreeByThreeGame(userInput);
        game.gameLoop();
        Assert.assertEquals(true, game.board.isWin());
    }

    @Test
    public void checksDiagonalWinForCross() {
        FakeIO userInput = new FakeIO(Arrays.asList("3", "1", "0", "1", "4", "6", "8", "2", "N"));
        Game game = getThreeByThreeGame(userInput);
        game.gameLoop();
        Assert.assertEquals(true, game.board.isWin());
    }

    @Test
    public void checksDiagonalWinForNought() {
        FakeIO userInput = new FakeIO(Arrays.asList("3", "1", "0", "2", "5", "6", "1", "4", "N"));
        Game game = getThreeByThreeGame(userInput);
        game.gameLoop();
        Assert.assertEquals(true, game.board.isWin());
    }

    @Test
    public void checksVerticalWinMiddleColumnForCross() {
        FakeIO userInput = new FakeIO(Arrays.asList("3", "1", "1", "0", "4", "6", "7", "8", "N"));
        Game game = getThreeByThreeGame(userInput);
        game.gameLoop();
        Assert.assertEquals(true, game.board.isWin());
    }

    @Test
    public void returnsFalseIfNoWin() {
        FakeIO userInput = new FakeIO(Arrays.asList("3", "1", "0", "1", "2", "4", "3", "5", "7", "6", "8", "N"));
        Game game = getThreeByThreeGame(userInput);
        game.gameLoop();
        Assert.assertEquals(false, game.board.isWin());
    }

    @Test
    public void checksVerticalWinFirstColumnForCross() {
        FakeIO userInput = new FakeIO(Arrays.asList("3", "1", "0", "1", "3", "8", "6", "7", "N"));
        Game game = getThreeByThreeGame(userInput);
        game.gameLoop();
        Assert.assertEquals(true, game.board.isWin());
    }

    @Test
    public void checksVerticalWinLastColumn() {
        FakeIO userInput = new FakeIO(Arrays.asList("3", "1", "2", "1", "5", "3", "8", "4", "N"));
        Game game = getThreeByThreeGame(userInput);
        game.gameLoop();
        Assert.assertEquals(true, game.board.checkWins(X));
        Assert.assertEquals(false, game.board.checkWins(O));
    }

    @Test
    public void setsUpThreeByThreeBoard() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, E, E,
                                                                         E, E, E,
                                                                         E, E, E));
        Assert.assertEquals(expectedBoard, threeByThreeBoard.markPlayer(0, X).contentsOfBoard());
    }

    @Test
    public void checksAllElementsOfRowStreamMatch() {
        Board threeByThree = new Board(Arrays.asList(X, X, O,
                                                     E, O, E,
                                                     O, E, X));
        Assert.assertTrue(threeByThree.hasWon(O));
    }

    @Test
    public void checksAllElementsOfARowMatch() {
        Board threeByThree = new Board(Arrays.asList(X, X, X,
                                                     X, O, E,
                                                     E, E, X));
        Assert.assertTrue(threeByThree.hasWon(X));
    }

    @Test
    public void checksAllElementsOfAColumnMatch() {
        Board threeByThree = new Board(Arrays.asList(X, O, X,
                                                     X, O, E,
                                                     X, E, X));
        Assert.assertTrue(threeByThree.hasWon(X));
    }

    @Test
    public void getsPositionOfCell() {
        Board newBoard = new Board(3);
        Assert.assertEquals(E, newBoard.get(0));
    }

    @Test(expected = RuntimeException.class)
    public void throwsAnExceptionIfMoveIsNotAvailable() {
        Board newBoard = new Board(3);
        newBoard.markPlayer(1337, X);
    }

    @Test
    public void largerThanBoardIsInvalid() {
        Board newBoard = new Board(3);
        Assert.assertFalse(newBoard.isPositionEmpty(37));
    }

    @Test
    public void returnsListOfValidMoves() {
        Board newBoard = new Board(3);
        List<Integer> validMoves = moves(0, 9);
        Assert.assertEquals(validMoves, newBoard.validMoves());
    }

    @Test
    public void checksBoardFull() {
        Assert.assertTrue(threeByThreeBoard.notFull());
    }

    @Test
    public void checksBoardNotFull() {
        Board board = new Board(3);
        Assert.assertTrue(board.notFull());
    }

    private Game getThreeByThreeGame(FakeIO userInput) {
        return new GameCreator(userInput).createGame();
    }

    private Game getFourByFourGame(FakeIO userInput) {
        return new GameCreator(userInput).createGame();
    }

    private List<Integer> moves(int from, int to) {
        List<Integer> result = new ArrayList<>();
        for (int i = from; i < to; i++) {
            result.add(i);
        }
        return result;
    }
}

