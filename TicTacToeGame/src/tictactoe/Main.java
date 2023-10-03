package tictactoe;

/**
 * Play the Tic Tac Toe game.
 */
public class Main {
  /**
   * Launches the Tic Tac Toe game.
   *
   * @param args Command-line arguments (not used).
   */
  public static void main(String[] args) {
    TicTacToe m = new TicTacToeModel();
    TicTacToeView v = new SwingTicTacToeView("Tic-Tac-Toe");
    TicTacToeController c = new SwingTicTacToeController(v, m);
    c.playGame();
  }
}