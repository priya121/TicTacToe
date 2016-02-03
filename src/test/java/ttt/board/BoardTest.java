package ttt.board;

import org.junit.Assert;
import org.junit.Test;
import ttt.Game;
import ttt.SetupBoard;
import ttt.Symbol;
import ttt.inputOutput.FakeIO;
import ttt.player.FakeComputerPlayer;
import ttt.player.HumanPlayer;
import ttt.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ttt.Symbol.*;

public class BoardTest {
    Board threeByThree = new Board(3);
    Board fourByFour = new Board(4);
    SetupBoard expected = new SetupBoard();
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
        Assert.assertEquals(rows, getSymbols(fourByFour.getRows()));
    }

    @Test
    public void firstRowOnFourByFourIsAWinForCross() {
        Board fourByFour = new Board(Arrays.asList(X, X, X, X,
                                                   E, E, E, E,
                                                   E, E, E, E,
                                                   E, E, E, E));
        Assert.assertTrue(fourByFour.checkWinningRows(X));
        Assert.assertEquals(false, fourByFour.checkWins(O));
    }

    @Test
    public void secondRowOnFourByFourIsNotAWinForCross() {
        Board newFourByFour = new Board(Arrays.asList(E, E, E, E,
                                                      X, X, X, E,
                                                      E, E, E, E,
                                                      E, E, E, E));
        Assert.assertEquals(fillRows(empty, threeCrosses, empty, empty), getSymbols(newFourByFour.getRows()));
        Assert.assertFalse(newFourByFour.checkWinningRows(X));
    }

    @Test
    public void findsThirdRowOnFourByFourIsAWinforCross() {
        Board newFourByFour = new Board(Arrays.asList(E, E, E, E,
                                                      E, E, E, E,
                                                      X, X, X, X,
                                                      E, E, E, E));
        Assert.assertEquals(fillRows(empty, empty, allCrosses, empty), getSymbols(newFourByFour.getRows()));
        Assert.assertTrue(newFourByFour.checkWinningRows(X));
    }

    @Test
    public void findsFourthRowOnFourByFourIsAWinForNought() {
        Board newFourByFour = new Board(Arrays.asList(E, E, E, E,
                                                      E, E, E, E,
                                                      E, E, E, E,
                                                      O, O, O, O));
        Assert.assertEquals(fillRows(empty, empty, empty, allNoughts), getSymbols(newFourByFour.getRows()));
        Assert.assertTrue(newFourByFour.checkWinningRows(O));
        Assert.assertFalse(newFourByFour.checkWinningRows(X));
    }

    @Test
    public void checksFirstColumnForWinForCross() {
        Board fourByFour = new Board(Arrays.asList(X, E, E, E,
                                                   X, E, E, E,
                                                   X, E, E, E,
                                                   E, E, E, E));
        Assert.assertEquals(fillColumns(threeCrosses, empty, empty, empty), getSymbols(fourByFour.getColumns()));
        Assert.assertFalse(fourByFour.checkWinningRows(X));
    }

    @Test
    public void checksThirdColumnForWin() {
        Board fourByFour = new Board(Arrays.asList(E, E, X, E,
                                                   E, E, X, E,
                                                   E, E, X, E,
                                                   E, E, X, E));
        Assert.assertEquals(fillColumns(empty, empty, allCrosses, empty), getSymbols(fourByFour.getColumns()));
        Assert.assertTrue(fourByFour.checkWinningColumns(X));
    }

    @Test
    public void checksDiagonalFromRightToLeftForWin() {
        Board board = new Board(Arrays.asList(X, E, E, E,
                                              E, X, E, E,
                                              E, E, X, E,
                                              E, E, E, X));
        Assert.assertEquals(fillDiagonals(allCrosses, empty), getSymbols(board.getDiagonals()));
        Assert.assertTrue(board.checkWinningDiagonals(X));
    }

    @Test
    public void findsFirstRowOnThreeByThreeIsAWin() {
        Board threeByThree = new Board(Arrays.asList(X, X, X,
                                                     E, E, E,
                                                     O, O, O));
        Assert.assertTrue(threeByThree.checkWinningRows(O));
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
        Assert.assertTrue(tenByTen.checkWinningRows(O));
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
        FakeIO humanMoves = new FakeIO(Arrays.asList("X", "X", "1", "5", "2", "N"));
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        Game game = getThreeByThreeGame(humanMoves, computerMoves(Arrays.asList(6, 7, 8), human));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksWinSecondRowForCross() {
        FakeIO humanMoves = new FakeIO(Arrays.asList("O", "O", "3", "4", "5", "N"));
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        Game game = getThreeByThreeGame(humanMoves, computerMoves(Arrays.asList(0, 6, 8), human));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksWinForFourByFourBoard() {
        FakeIO humanMoves = new FakeIO(Arrays.asList("X", "4", "X", "4", "5", "6", "7", "N"));
        HumanPlayer human = new HumanPlayer(humanMoves, fourByFour);
        Game game = getFourByFourGame(humanMoves, computerMoves(Arrays.asList(0, 15, 8, 1), human));
        game.gameLoop();
        Assert.assertEquals(true, fourByFour.isWin());
    }

    @Test
    public void checksNoughtWinsDiagonally() {
        FakeIO humanMoves = new FakeIO(Arrays.asList("X", "1", "3", "7", "N"));
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        Game game = getThreeByThreeGame(humanMoves, computerMoves(Arrays.asList(0, 4, 8), human));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksAnotherDiagonalWin() {
        FakeIO humanMoves = new FakeIO(Arrays.asList("X", "1", "3", "7", "N"));
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        Game game = getThreeByThreeGame(humanMoves, computerMoves(Arrays.asList(0, 4, 8), human));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksDiagonalWinForCross() {
        FakeIO humanMoves = new FakeIO(Arrays.asList("X", "0", "4", "8", "N"));
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        Game game = getThreeByThreeGame(humanMoves, computerMoves(Arrays.asList(1, 6, 2), human));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksDiagonalWinForNought() {
        FakeIO humanMoves = new FakeIO(Arrays.asList("X", "3", "0", "5", "N"));
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        Game game = getThreeByThreeGame(humanMoves, computerMoves(Arrays.asList(2, 6, 4), human));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksVerticalWinMiddleColumnForCross() {
        FakeIO humanMoves = new FakeIO(Arrays.asList("X", "1", "4", "7", "N"));
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        Game game = getThreeByThreeGame(humanMoves, computerMoves(Arrays.asList(0, 6, 8), human));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void returnsFalseIfNoWin() {
        FakeIO humanMoves = new FakeIO(Arrays.asList("X", "0", "2", "3", "7", "8", "N"));
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        Game game = getThreeByThreeGame(humanMoves, computerMoves(Arrays.asList(1, 4, 5, 6), human));
        game.gameLoop();
        Assert.assertEquals(false, threeByThree.isWin());
    }

    @Test
    public void checksVerticalWinFirstColumnForCross() {
        FakeIO humanMoves = new FakeIO(Arrays.asList("X", "0", "3", "6", "N"));
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        Game game = getThreeByThreeGame(humanMoves, computerMoves(Arrays.asList(1, 8, 7, 2), human));
        game.gameLoop();
        Assert.assertEquals(true, threeByThree.isWin());
    }

    @Test
    public void checksVerticalWinLastColumn() {
        FakeIO humanMoves = new FakeIO(Arrays.asList("X", "2", "5", "8", "N"));
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        Game game = getThreeByThreeGame(humanMoves, computerMoves(Arrays.asList(1, 3, 4), human));
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

    private FakeComputerPlayer getFakeComputerMoves(List<Integer> input, Player opponent) {
        return new FakeComputerPlayer(input, opponent);
    }

    private Game getThreeByThreeGame(FakeIO humanMoves, FakeComputerPlayer computerMoves) {
        HumanPlayer human = new HumanPlayer(humanMoves, threeByThree);
        return new Game(threeByThree, humanMoves, human, computerMoves);
    }

    private Game getFourByFourGame(FakeIO humanMoves, FakeComputerPlayer computerMoves) {
        HumanPlayer human = new HumanPlayer(humanMoves, fourByFour);
        return new Game(fourByFour, humanMoves, human, computerMoves);
    }

    public FakeIO humanMoves(List<String> moves) {
        return getFakeIO(moves);
    }

    public FakeComputerPlayer computerMoves(List<Integer> moves, Player opponent) {
        return getFakeComputerMoves(moves, opponent);
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

    private List<List<Symbol>> getSymbols(List<Line> lines) {
        List<List<Symbol>> cells = new ArrayList<>();
        for (Line line : lines) {
            cells.add(line.getSymbols());
        }
        return cells;
        }
}

