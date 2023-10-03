package tictactoe;

import java.awt.event.ActionListener;

/**
 * Defines the contract for the view in a Tic-Tac-Toe game.
 * View is responsible for displaying the game board, moves, turn information, and game results.
 */
public interface TicTacToeView {

  /**
   * Displays the initial state of the Tic Tac Toe game board.
   * This method is typically called at the start of the game to show the empty board.
   */
  void display();


  /**
   * Adds an ActionListener to the view, allowing it to receive user input events.
   * The ActionListener is used to capture user interactions.
   *
   * @param listener The ActionListener to be added.
   */
  void addActionListener(ActionListener listener);

  /**
   * Displays a player's move on the game board.
   * This method is called when a player makes a valid move and
   * updates the corresponding cell on the board.
   *
   * @param row         The row index of the move.
   * @param col         The column index of the move.
   * @param currentPlayer The symbol representing the current player (e.g., "X" or "O").
   */
  void displayMove(int row, int col, String currentPlayer);

  /**
   * Updates the label indicating whose turn it is to play.
   *
   * @param turn The symbol representing the player whose turn it is (e.g., "X" or "O").
   */
  void updateTurnLabel(String turn);

  /**
   * Displays a message indicating the result of the game.
   * This message could include information about a win or a tie.
   *
   * @param result The message describing the game result.
   */
  void displayMessage(String result);
}
