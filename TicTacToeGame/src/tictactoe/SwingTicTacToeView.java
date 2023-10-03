package tictactoe;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * Implements the graphical user interface (GUI) view for a Tic Tac Toe game using Swing components.
 * It displays the game board, player moves, turn information, and game results.
 */
public class SwingTicTacToeView extends JPanel implements TicTacToeView {
  private JFrame frame;
  private JLabel turn;
  private JButton [][] cells;
  private JLabel winResult;

  /**
   * Constructs a SwingTicTacToeView instance with the specified title.
   *
   * @param title The title of the game window.
   */
  public SwingTicTacToeView(String title) {
    frame = new JFrame(title);
    frame.setSize(800, 600);
    frame.setLocation(200, 200);
    //setPreferredSize(new Dimension(800, 600));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel gridPanel = new JPanel();
    gridPanel.setSize(800, 600);
    this.turn = new JLabel("Turn: X");
    turn.setFont(new Font("Arial", Font.BOLD, 18));
    this.winResult = new JLabel("");
    this.cells = new JButton[3][3];
    gridPanel.setLayout(new GridLayout(3, 3));
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        this.cells[i][j] = new JButton();
        this.cells[i][j].setPreferredSize(new Dimension(100, 100));
        gridPanel.add(this.cells[i][j]);
      }
    }
    this.add(turn);
    this.add(winResult);

    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(turn, BorderLayout.NORTH);
    frame.getContentPane().add(gridPanel, BorderLayout.CENTER);
    frame.getContentPane().add(winResult, BorderLayout.SOUTH);
    frame.pack();
  }

  @Override
  public void display() {
    frame.setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener listener) {
    for (int row = 0; row < cells.length; row++) {
      for (int col = 0; col < cells[row].length; col++) {
        this.cells[row][col].addActionListener(listener);
        this.cells[row][col].setActionCommand(String.format("%d, %d", row, col));
      }
    }
  }

  @Override
  public void displayMove(int row, int col, String currentPlayer) {
    this.cells[row][col].setText(currentPlayer);
    this.cells[row][col].setFont(new Font("Arial", Font.PLAIN, 36));
    this.cells[row][col].setEnabled(false);
  }

  @Override
  public void displayMessage(String result) {
    winResult.setFont(new Font("Arial", Font.BOLD, 18));
    this.winResult.setText(result);
  }

  @Override
  public void updateTurnLabel(String turn) {
    this.turn.setText("Turn: " + turn);
  }

}
