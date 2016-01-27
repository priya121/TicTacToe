package ttt.board;

import org.junit.Assert;
import org.junit.Test;
import ttt.Game;
import ttt.GameSetup;
import ttt.Symbol;
import ttt.inputOutput.FakeIO;
import ttt.player.FakeComputerPlayer;
import ttt.player.HumanPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.*;
public class BoardTest {
    Board threeByThree = new Board(3);
    Board fourByFour = new Board(4);
    GameSetup expected = new GameSetup();
    List<Symbol> empty = Arrays.asList(E, E, E, E);
    List<Symbol> allCrosses = Arrays.asList(X, X, X, X);
    List<Symbol> allNoughts = Arrays.asList(O, O, O, O);
    List<Symbol> threeCrosses = Arrays.asList(X, X, X, E);

    @Test
    public void returnsEmptyThreeByThreeBoard() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(E, E, E,
                                                                         E, E, E,
                                                                         E, E, E));
        Assert.assertEquals(expectedBoard, threeByThree.contentsOfBoard());
    }

    @Test
    public void returnsEmptyFourByFour() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(E, E, E, E,
                                                                         E, E, E, E,
                                                                         E, E, E, E,
                                                                         E, E, E, E));
        Assert.assertEquals(expectedBoard, fourByFour.contentsOfBoard());
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
    public void returnsFourByFourWithCrossesInFirstRow() {
        Board fourByFour = new Board(Arrays.asList(X, X, X, X,
                                                   E, E, E, E,
                                                   E, E, E, E,
                                                   E, E, E, E));
        List<List<Symbol>> rows = fillRows(allCrosses, empty, empty, empty);
        Assert.assertEquals(rows, fourByFour.getRows());
    }

    @Test
    public void firstRowOnFourByFourIsAWinForCross() {
        Board fourByFour = new Board(Arrays.asList(X, X, X, X,
                                                   E, E, E, E,
                                                   E, E, E, E,
                                                   E, E, E, E));
        Assert.assertTrue(fourByFour.checkWinningLine(fourByFour.getRows(), X));
        Assert.assertEquals(false, fourByFour.checkWins(O));
    }

    @Test
    public void secondRowOnFourByFourIsNotAWinForCross() {
        Board newFourByFour = new Board(Arrays.asList(E, E, E, E,
                                                      X, X, X, E,
                                                      E, E, E, E,
                                                      E, E, E, E));
        Assert.assertEquals(fillRows(empty, threeCrosses, empty, empty), newFourByFour.getRows());
        Assert.assertFalse(newFourByFour.checkWinningLine(newFourByFour.getRows(), X));
    }

    @Test
    public void findsThirdRowOnFourByFourIsAWinforCross() {
        Board newFourByFour = new Board(Arrays.asList(E, E, E, E,
                                                      E, E, E, E,
                                                      X, X, X, X,
                                                      E, E, E, E));
        Assert.assertEquals(fillRows(empty, empty, allCrosses, empty), newFourByFour.getRows());
        Assert.assertTrue(newFourByFour.checkWinningLine(newFourByFour.getRows(), X));
    }

    @Test
    public void findsFourthRowOnFourByFourIsAWinForNought() {
        Board newFourByFour = new Board(Arrays.asList(E, E, E, E,
                                                      E, E, E, E,
                                                      E, E, E, E,
                                                      O, O, O, O));
        Assert.assertEquals(fillRows(empty, empty, empty, allNoughts), newFourByFour.getRows());
        Assert.assertTrue(newFourByFour.checkWinningLine(newFourByFour.getRows(), O));
        Assert.assertFalse(newFourByFour.checkWinningLine(newFourByFour.getRows(), X));
    }

    @Test
    public void checksFirstColumnForWinForCross() {
        Board fourByFour = new Board(Arrays.asList(X, E, E, E,
                                                   X, E, E, E,
                                                   X, E, E, E,
                                                   E, E, E, E));
        Assert.assertEquals(fillColumns(threeCrosses, empty, empty, empty), fourByFour.getColumns());
        Assert.assertFalse(fourByFour.checkWinningLine(fourByFour.getRows(), X));
    }

    @Test
    public void checksThirdColumnForWin() {
        Board fourByFour = new Board(Arrays.asList(E, E, X, E,
                                                   E, E, X, E,
                                                   E, E, X, E,
                                                   E, E, X, E));
        Assert.assertEquals(fillColumns(empty, empty, allCrosses, empty), fourByFour.getColumns());
        Assert.assertTrue(fourByFour.checkWinningLine(fourByFour.getColumns(), X));
    }

    @Test
    public void checksDiagonalFromRightToLeftForWin() {
        Board board = new Board(Arrays.asList(X, E, E, E,
                                              E, X, E, E,
                                              E, E, X, E,
                                              E, E, E, X));
        Assert.assertEquals(fillDiagonals(allCrosses, empty), board.getDiagonals());
        Assert.assertTrue(board.checkWinningLine(board.getDiagonals(), X));
    }

    @Test
    public void findsFirstRowOnThreeByThreeIsAWin() {
        Board threeByThree = new Board(Arrays.asList(X, X, X,
                                                     E, E, E,
                                                     O, O, O));
        Assert.assertTrue(threeByThree.checkWinningLine((threeByThree.getRows()), O));
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
        Assert.assertTrue(tenByTen.checkWinningLine(tenByTen.getRows(), O));
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
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("1", "5", "2")), computerMoves(Arrays.asList(6, 7, 8)));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksWinSecondRowForCross() {
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("3", "4", "5")), computerMoves(Arrays.asList(0, 6)));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksWinForFourByFourBoard() {
        Game game = getFourByFourGame(humanMoves(Arrays.asList("4", "5", "6", "7")), computerMoves(Arrays.asList(0, 15, 8, 1)));
        game.gameLoop();
        Assert.assertEquals(true, fourByFour.isWin());
    }

    @Test
    public void checksNoughtWinsDiagonally() {
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("1", "3", "7")), computerMoves(Arrays.asList(0, 4, 8)));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksAnotherDiagonalWin() {
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("3", "1", "3", "7")), computerMoves(Arrays.asList(0, 4, 8)));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksDiagonalWinForCross() {
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("0", "4", "8")), computerMoves(Arrays.asList(1, 6)));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksDiagonalWinForNought() {
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("3", "0", "5")), computerMoves(Arrays.asList(2, 6, 4)));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksVerticalWinMiddleColumnForCross() {
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("1", "4", "7")), computerMoves(Arrays.asList(0, 6)));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void returnsFalseIfNoWin() {
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("0", "2", "3", "7", "8")), computerMoves(Arrays.asList(1, 4, 5, 6)));
        game.gameLoop();
        Assert.assertEquals(false, threeByThree.isWin());
    }

    @Test
    public void checksVerticalWinFirstColumnForCross() {
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("0", "3", "6")), computerMoves(Arrays.asList(1, 8)));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksVerticalWinLastColumn() {
        Game game = getThreeByThreeGame(humanMoves(Arrays.asList("2", "1", "5", "3", "8")), computerMoves(Arrays.asList(1, 3)));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.checkWins(X));
        Assert.assertEquals(false, threeByThree.checkWins(O));
    }

    @Test
    public void setsUpThreeByThreeBoard() {
        List<Symbol> expectedBoard = expected.placeSymbols(Arrays.asList(X, E, E,
                                                                         E, E, E,
                                                                         E, E, E));
        Assert.assertEquals(expectedBoard, threeByThree.markPlayer(0, X));
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
        Assert.assertTrue(threeByThree.notFull());
    }

    @Test
    public void checksBoardNotFull() {
        Board board = new Board(3);
        Assert.assertTrue(board.notFull());
    }

    private FakeIO getFakeIO(List<String> input) {
        return new FakeIO(input);
    }

    private FakeComputerPlayer getFakeComputerMoves(List<Integer> input) {
        return new FakeComputerPlayer(input);
    }

    private Game getThreeByThreeGame(FakeIO humanMoves, FakeComputerPlayer computerMoves) {
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        return new Game(threeByThree, humanMoves, computerMoves, human);
    }

    private Game getFourByFourGame(FakeIO humanMoves, FakeComputerPlayer computerMoves) {
        HumanPlayer human = new HumanPlayer(humanMoves, fourByFour);
        return new Game(fourByFour, humanMoves, computerMoves, human);
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }

    public FakeComputerPlayer computerMoves(List<Integer> moves) {
        return getFakeComputerMoves(moves);
    }

    private List<List<Symbol>> fillRows(List<Symbol> firstRow, List<Symbol> secondRow, List<Symbol> thirdRow, List<Symbol> fourthRow) {
        List<List<Symbol>> rows = new ArrayList<>();
        rows.add(firstRow);
        rows.add(secondRow);
        rows.add(thirdRow);
        rows.add(fourthRow);
        return rows;
    }

    private List<List<Symbol>> fillColumns(List<Symbol> firstColumn, List<Symbol> secondColumn, List<Symbol> thirdColumn, List<Symbol> fourthColumn) {
        List<List<Symbol>> columns = new ArrayList<>();
        columns.add(firstColumn);
        columns.add(secondColumn);
        columns.add(thirdColumn);
        columns.add(fourthColumn);
        return columns;
    }

    private List<List<Symbol>> fillDiagonals(List<Symbol> firstDiagonal, List<Symbol> secondDiagonal) {
        List<List<Symbol>> diagonals = new ArrayList<>();
        diagonals.add(firstDiagonal);
        diagonals.add(secondDiagonal);
        return diagonals;
    }

    private List<Integer> moves(int from, int to) {
        List<Integer> result = new ArrayList<>();
        for (int i = from; i < to; i++) {
            result.add(i);
        }
        return result;
    }
}

