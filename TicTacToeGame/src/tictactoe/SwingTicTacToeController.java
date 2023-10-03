package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the game controller for a Tic Tac Toe game
 * Using a Swing-based graphical user interface (GUI).
 * It manages the game logic and user interactions,
 * coordinating actions between the model and the view.
 */
public class SwingTicTacToeController implements TicTacToeController, ActionListener {
  private TicTacToe model;
  private TicTacToeView view;
  private Player currentPlayer = Player.X;

  /**
   * Constructs a Tic Tac Toe controller instance with the provided view and model.
   *
   * @param view  The view component responsible for displaying the game board and interactions.
   * @param model The model component representing the Tic Tac Toe game logic.
   */
  public SwingTicTacToeController(TicTacToeView view, TicTacToe model) {
    this.model = model;
    this.view = view;
    view.addActionListener(this);
  }

  @Override
  public void playGame() {
    view.display();
  }

  /**
   * Handles user actions triggered by the view's components.
   * This method is invoked when a player makes a move on the game board.
   *
   * @param e The <code>ActionEvent</code> representing the user's action.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (!model.isGameOver()) {
      String data = e.getActionCommand();
      String[] parts = data.split(",");
      int row = Integer.parseInt(parts[0].trim());
      int col = Integer.parseInt(parts[1].trim());
      model.move(row, col);
      view.displayMove(row, col, currentPlayer.toString());
      currentPlayer = model.getTurn();
      view.updateTurnLabel(currentPlayer.toString());
      if (model.getWinner() != null) {
        view.displayMessage("Game is over! " + model.getWinner().toString() + " wins.");
      } else if (model.getWinner() == null && model.isGameOver()) {
        view.displayMessage("Game is over! Tie game.");
      } else {
        view.displayMessage("");
      }
    }
  }
}
