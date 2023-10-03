import org.junit.Test;
import tictactoe.Player;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeModel;

import static org.junit.Assert.*;

/**
 * Test cases for the tic tac toe model. Verifying that game state is properly managed, and
 * all game actions are properly validated.
 */
public class TicTacToeModelTest {

  private TicTacToe ttt1 = new TicTacToeModel();
  private TicTacToe exampleGame = new TicTacToeModel();

  /**
   * Test for the move() method, verifying that the player turn is updated correctly.
   */
  @Test
  public void testMove() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
  }

  /**
   * Test for a horizontal win scenario, verifying that the game is correctly declared as over,
   * the winner is determined, and the game board is updated accordingly.
   */
  @Test
  public void testHorizontalWin() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(1, 0); // O takes middle left
    ttt1.move(0, 1); // X takes upper middle
    assertNull(ttt1.getWinner());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(0, 2); // X takes upper right
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.X, ttt1.getWinner());
    assertEquals(" X | X | X\n"
                          + "-----------\n"
                          + " O |   |  \n"
                          + "-----------\n"
                          + " O |   |  ", ttt1.toString());
  }

  /**
   * Test for a diagonal win scenario, verifying that the game is correctly declared as over,
   * the winner is determined, and the game board is updated accordingly.
   */
  @Test
  public void testDiagonalWin() {
    diagonalWinHelper();
    assertTrue(ttt1.isGameOver());
    assertEquals(Player.O, ttt1.getWinner());
    assertEquals(" X | X | O\n"
            + "-----------\n"
            + " X | O |  \n"
            + "-----------\n"
            + " O |   |  ", ttt1.toString());
  }

  // set up situation where game is over, O wins on the diagonal, board is not full
  private void diagonalWinHelper() {
    ttt1.move(0, 0); // X takes upper left
    assertFalse(ttt1.isGameOver());
    ttt1.move(2, 0); // O takes lower left
    ttt1.move(1, 0); // X takes middle left
    assertNull(ttt1.getWinner());
    ttt1.move(1, 1); // O takes center
    ttt1.move(0, 1); // X takes upper middle
    ttt1.move(0, 2); // O takes upper right
  }

  /**
   * Test for an invalid move scenario, verifying that an exception is
   * thrown and the game state remains unchanged.
   */
  @Test
  public void testInvalidMove() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    assertEquals(Player.X, ttt1.getMarkAt(0, 0));
    try {
      ttt1.move(0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Position occupied", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
    try {
      ttt1.move(-1, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Position is out of bounds!", iae.getMessage());
      assertTrue(iae.getMessage().length() > 0);
    }
  }

  /**
   * Test for attempting to make a move after the game is already
   * over, verifying that an exception is thrown.
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveAttemptAfterGameOver() {
    diagonalWinHelper();
    ttt1.move(2, 2); // 2,2 is an empty position
  }

  /**
   * Test for a cats game scenario, verifying that the game is correctly declared as over,
   * there is no winner, and the game board is updated accordingly.
   */
  @Test
  public void testCatsGame() {
    ttt1.move(0, 0);
    assertEquals(Player.O, ttt1.getTurn());
    ttt1.move(1, 1);
    assertEquals(Player.X, ttt1.getTurn());
    ttt1.move(0, 2);
    ttt1.move(0, 1);
    ttt1.move(2, 1);
    ttt1.move(1, 0);
    ttt1.move(1, 2);
    ttt1.move(2, 2);
    ttt1.move(2, 0);
    assertTrue(ttt1.isGameOver());
    assertNull(ttt1.getWinner());
    assertEquals(" X | O | X\n"
            + "-----------\n"
            + " O | O | X\n"
            + "-----------\n"
            + " X | X | O", ttt1.toString());
  }

  /**
   * Test for getting the mark at a valid position.
   */
  @Test
  public void testValidGetMarkAt() {
    ttt1.move(2, 0);
    assertEquals(Player.X, ttt1.getMarkAt(2, 0));
  }

  /**
   * Test for getting the mark at an invalid row, verifying that an exception is thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtRow() {
    ttt1.getMarkAt(-12, 0);
  }

  /**
   * Test for getting the mark at an invalid column, verifying that an exception is thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtCol() {
    ttt1.getMarkAt(0, -30);
  }

  /**
   * Test for the getBoard() method, verifying that the returned game board is a separate copy
   * and not affected by mutations.
   */
  @Test
  public void testGetBoard() {
    diagonalWinHelper();
    Player[][] bd = ttt1.getBoard();
    assertEquals(Player.X, bd[0][0]);
    assertEquals(Player.O, bd[1][1]);
    assertEquals(Player.X, bd[0][1]);

    // attempt to cheat by mutating board returned by getBoard()
    // check correct preconditions
    assertEquals(Player.O, bd[2][0]);
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    bd[2][0] = Player.X;  // mutate
    // check correct post conditions
    assertEquals(Player.O, ttt1.getMarkAt(2, 0));
    Player[][] bd2 = ttt1.getBoard();
    assertEquals(Player.O, bd2[2][0]);
  }

  // TODO: test case where board is full AND there is a winner
  /**
   * Test for a scenario where the game board is full and there is a winner,
   * verifying that the game is correctly declared as over and the winner is determined.
   */
  @Test
  public void testFullBoardWithWinner() {
    assertFalse(exampleGame.isGameOver());

    assertEquals(Player.X, exampleGame.getTurn());
    exampleGame.move(0, 0);
    assertEquals(Player.O, exampleGame.getTurn());
    exampleGame.move(1, 0);
    assertEquals(Player.X, exampleGame.getTurn());
    exampleGame.move(1, 1);
    assertEquals(Player.O, exampleGame.getTurn());
    exampleGame.move(2, 2);
    assertEquals(Player.X, exampleGame.getTurn());
    exampleGame.move(1, 2);
    assertEquals(Player.O, exampleGame.getTurn());
    exampleGame.move(0, 2);
    assertEquals(Player.X, exampleGame.getTurn());
    exampleGame.move(0, 1);
    assertEquals(Player.O, exampleGame.getTurn());
    exampleGame.move(2, 0);
    assertEquals(Player.X, exampleGame.getTurn());
    exampleGame.move(2, 1);

    assertTrue(exampleGame.isGameOver());
    assertEquals(Player.X, exampleGame.getWinner());

    assertEquals(Player.O, exampleGame.getMarkAt(0, 2));
    assertEquals(Player.X, exampleGame.getMarkAt(1, 1));

    Player[][] bd = exampleGame.getBoard();
    assertEquals(Player.X, bd[0][0]);
    assertEquals(Player.O, bd[1][0]);
    assertEquals(Player.X, bd[2][1]);

    assertEquals(" X | X | O\n"
        + "-----------\n"
        + " O | X | X\n"
        + "-----------\n"
        + " O | X | O", exampleGame.toString());
  }
}
