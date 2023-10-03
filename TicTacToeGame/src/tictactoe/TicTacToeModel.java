package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a game of Tic Tac Toe played on a three-by-three grid with two players.
 * The objective of the game is to achieve three markers in a row either vertically,
 * horizontally, or diagonally. Player X goes first.
 */
public class TicTacToeModel implements tictactoe.TicTacToe {
  // add your implementation here
  private Player[][] board;
  private Player turn;

  /**
   * Creates a new instance of the TicTacToeModel class.
   * Initializes the game board with a size of 3x3 and sets the initial player turn to X.
   */
  public TicTacToeModel() {
    turn = Player.X;
    board = new Player[3][3];
    for (int i = 0; i < board.length; i++) {
      //Arrays.fill(board[i], null);
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = null;
      }
    }
  }

  /**
   * Returns a string representation of the current state of the tic-tac-toe board.
   * The board is formatted as a 3x3 grid, with each cell represented by the corresponding
   * player's mark (X, O), or an empty space if the cell is unmarked.
   *
   * @return a string representation of the tic-tac-toe board
   */
  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
            row -> " " + Arrays.stream(row).map(
                p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
        .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful built-in String.join method.
  }

  @Override public void move(int r, int c) throws IllegalArgumentException, IllegalStateException {
    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
      throw new IllegalArgumentException("Position is out of bounds!");
    } else if (this.isGameOver()) {
      throw new IllegalStateException("Game is over!");
    } else if (board[r][c] != null) {
      throw new IllegalArgumentException("Position occupied");
    } else {
      board[r][c] = this.getTurn();
      this.turn = this.getTurn() == Player.X ? Player.O : Player.X;
    }
  }

  @Override public Player getTurn() {
    return this.turn;
  }

  @Override public boolean isGameOver() {
    if (this.isFull()) {
      return true;
    } else if (this.checkRow() || this.checkCol() || this.checkDiag()) {
      return true;
    }
    return false;
  }

  @Override public Player getWinner() {
    if (this.checkRow() || this.checkCol() || this.checkDiag()) {
      Player winner = this.getTurn() == Player.X ? Player.O : Player.X;
      return winner;
    } else {
      return null;
    }
  }

  @Override public Player[][] getBoard() {
    Player[][] newBoard = new Player[board.length][board[0].length];
    for (int i = 0; i < board.length; i++) {
      System.arraycopy(this.board[i], 0, newBoard[i], 0, board[i].length);
      /*
      for (int j = 0; j < board[i].length; j++) {
        newBoard[i][j] = board[i][j];
      }
     */
    }
    return newBoard;
  }

  @Override public Player getMarkAt(int r, int c) throws IllegalArgumentException {
    if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) {
      throw new IllegalArgumentException("Position is out of bounds!");
    }

    return board[r][c];
  }

  /**
   * Checks if the game board is full, i.e., all positions are occupied by players.
   *
   * @return true if the game board is full, false otherwise
   */
  private boolean isFull() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == null) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Checks if there is a winning row on the game board.
   *
   * @return true if there is a winning row, false otherwise
   */
  private boolean checkRow() {
    for (int i = 0; i < 3; i++) {
      if (board[i][0] != null) {
        if ((board[i][0].equals(board[i][1])) && (board[i][1].equals(board[i][2]))) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if there is a winning column on the game board.
   *
   * @return true if there is a winning column, false otherwise
   */
  private boolean checkCol() {
    for (int i = 0; i < 3; i++) {
      if (board[0][i] != null) {
        if ((board[0][i].equals(board[1][i])) && (board[1][i].equals(board[2][i]))) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if there is a winning diagonal on the game board.
   *
   * @return true if there is a winning diagonal, false otherwise
   */
  private boolean checkDiag() {
    if (board[0][0] != null && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
      return true;
    }

    if (board[0][2] != null && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
      return true;
    }

    return false;
  }
}

