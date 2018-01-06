package assignment2017;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.IllegalColumnException;
import assignment2017.codeprovided.IllegalRowException;

/**
 * MyGameState class extends Connect4GameState Implements all the abstracts methods declared in
 * Connect4GameState Also contains the configuration of the board and whose turn it is.
 * 
 * @author Georgica Bors
 *
 */
public class MyGameState extends Connect4GameState {

  // Instance variables
  public int[][] board;
  public int currentTurn;

  /**
   * No-arg constructor
   */
  public MyGameState() {
    board = new int[NUM_ROWS][NUM_COLS];
  }

  /**
   * Starts the game. Initializes every grid position in the board to EMPTY and sets the current
   * turn to RED
   */
  public void startGame() {

    for (int i = 0; i < NUM_ROWS; i++) {
      for (int j = 0; j < NUM_COLS; j++) {
        board[i][j] = EMPTY;
      }
    }
    currentTurn = RED;
  }

  /**
   * Drops a counter into a slot on the board. The colour of the counter depends on whose turn it is
   * 
   * @param col the column in which to drop the counter, in the range 0-6
   * @throws ColumnFullException if the column denoted by col is full
   * @throws IllegalColumnException if col is not in the range 0-6
   */
  public void move(int col) throws ColumnFullException, IllegalColumnException {
    // If the col is out of range throw an exception
    if (col > 6 || col < 0)
      throw new IllegalColumnException(col);

    if (this.isColumnFull(col))
      throw new ColumnFullException(col);

    int i = 0;
    // Look for the first non-empty slot on the column
    while (this.board[i][col] != EMPTY) {

      i++;
    }

    board[i][col] = this.currentTurn;
    // Change the currentTurn each time a player make a move
    if (this.currentTurn == RED)
      this.currentTurn = YELLOW;
    else
      this.currentTurn = RED;

  }

  /**
   * Returns whose turn is it
   * 
   * @return the constant RED if it is red's turn, else YELLOW
   */
  public int whoseTurn() {

    return this.currentTurn;
  }

  /**
   * Returns a constant denoting the status of the slot at the position denoted by the col and row
   * parameters
   * 
   * @param col the column of the position being queried
   * @param row the row of the position being queried
   * @return the EMPTY constant if the slot is empty, the RED constant if the slot is filled by a
   *         red counter, the YELLOW constant if is yellow
   * @throws IllegalColumnException if col is not in the range 0-6
   * @throws IllegalRowException if col is not in the range 0-5
   */
  public int getCounterAt(int col, int row) throws IllegalColumnException, IllegalRowException {
    // Throw an exception is the column number
    // or the row number is out of range
    if (col > 6 || col < 0)
      throw new IllegalColumnException(col);

    if (row > 5 || row < 0)
      throw new IllegalRowException(row);

    return board[row][col];
  }

  /**
   * Returns whether the board is full and the game has ended in a tie
   * 
   * @return true if the board is full, else false
   */
  public boolean isBoardFull() {
    boolean boardFull = true;
    // Check each column
    for (int i = 0; i < NUM_COLS; i++) {
      if (this.isColumnFull(i) != true)
        boardFull = false;
    }

    return boardFull;
  }

  /**
   * Returns whether the column denoted by the col parameter is full or not
   * 
   * @param col the column being queried (in the range 0-6)
   * @return true if the column denoted by col is full of counters, else false
   * @throws IllegalColumnException if col is not in the range 0-6
   */
  public boolean isColumnFull(int col) throws IllegalColumnException {
    // Throw an exception if the column number is out of range
    if (col > 6 || col < 0)
      throw new IllegalColumnException(col);

    boolean columnFull = true;
    // Check if the column contains an empty slot
    for (int i = 0; i < NUM_ROWS; i++) {
      if (this.board[i][col] == EMPTY)
        columnFull = false;
    }

    return columnFull;
  }

  private int checkVertically() {

    // Check vertically
    // If two consecutive elements are equal, the third and fourth elements
    // are checked
    // If all four of them are equal then there is a winner
    // Return the winner

    for (int j = 0; j < NUM_COLS; j++) {

      if (this.board[0][j] != EMPTY) {

        for (int i = 0; i < NUM_ROWS - 3; i++) {

          if (this.board[i][j] == this.board[i + 1][j] && this.board[i][j] != EMPTY)

            if (this.board[i][j] == this.board[i + 2][j]
                && this.board[i][j] == this.board[i + 3][j]) {
              return board[i][j];
            }
        }
      }
    }
    return EMPTY;
  }

  private int checkHorizontally() {

    // Check horizontally
    // If two consecutive elements are equal, the third and fourth elements
    // are checked
    // If all four of them are equal then there is a winner
    // Return the winner
    for (int i = 0; i < NUM_ROWS; i++) {
      for (int j = 0; j < NUM_COLS - 3; j++) {

        if (this.board[i][j] == this.board[i][j + 1] && this.board[i][j] != EMPTY) {

          if (this.board[i][j] == this.board[i][j + 2]
              && this.board[i][j] == this.board[i][j + 3]) {
            return board[i][j];
          }
        }

      }
    }
    return EMPTY;
  }

  private int checkDiagonal() {
    // Diagonal forward slash - lower part
    // If two consecutive elements are equal, the third and fourth elements
    // are checked
    // If all four of them are equal then there is a winner
    // Return the winner
    for (int j = 1; j < NUM_COLS - 3; j++) {
      int i = 0;
      int k = j;

      while (i < NUM_COLS - j - 3) {
        if (this.board[i][k] == this.board[i + 1][k + 1] && this.board[i][k] != EMPTY) {

          if (this.board[i][k] == this.board[i + 2][k + 2]
              && this.board[i][k] == this.board[i + 3][k + 3]) {
            return board[i][k];
          }
        }
        i++;
        k++;
      }
    }

    // Back slash - lower part
    // If two consecutive elements are equal, the third and fourth elements
    // are checked
    // If all four of them are equal then there is a winner
    // Return the winner
    int counter = 3;
    for (int j = 3; j < NUM_COLS - 1; j++) {
      int i = 0;
      int k = j;

      while (i < NUM_COLS - counter - 3) {
        if (this.board[i][k] == this.board[i + 1][k - 1] && this.board[i][k] != EMPTY) {

          if (this.board[i][k] == this.board[i + 2][k - 2]
              && this.board[i][k] == this.board[i + 3][k - 3]) {
            return board[i][k];
          }
        }
        i++;
        k--;
      }
      counter--;
    }

    // Forward slash - upper part
    // If two consecutive elements are equal, the third and fourth elements
    // are checked
    // If all four of them are equal then there is a winner
    // Return the winner

    for (int i = 0; i < NUM_ROWS - 3; i++) {
      int j = 0;
      int k = i;

      while (j < NUM_ROWS - i - 3) {
        if (this.board[k][j] == this.board[k + 1][j + 1] && this.board[k][j] != EMPTY) {

          if (this.board[k][j] == this.board[k + 2][j + 2]
              && this.board[k][j] == this.board[k + 3][j + 3]) {
            return board[k][j];
          }
        }
        j++;
        k++;
      }
    }
    // Diagonal back slash upper right
    // If two consecutive elements are equal, the third and fourth elements
    // are checked
    // If all four of them are equal then there is a winner
    // Return the winner

    for (int i = 0; i < NUM_ROWS - 3; i++) {
      int j = NUM_COLS - 1;
      int k = i;

      while (j > i + 3) {
        if (this.board[k][j] == this.board[k + 1][j - 1] && this.board[k][j] != EMPTY) {

          if (this.board[k][j] == this.board[k + 2][j - 2]
              && this.board[k][j] == this.board[k + 3][j - 3]) {
            return board[k][j];
          }
        }
        j--;
        k++;
      }
    }
    return EMPTY;
  }

  /**
   * Indicates whether the game has been won, and by whom
   * 
   * @return the constant EMPTY if there is no winner yet, else the constant RED if the red player
   *         has four in a row, or the YELLOW constant if it is yellow that has won
   */

  public int getWinner() {

    if (checkVertically() != EMPTY)
      return checkVertically();

    else if (checkHorizontally() != EMPTY)
      return checkHorizontally();

    else if (checkDiagonal() != EMPTY)
      return checkDiagonal();

    else {
      return EMPTY;
    }
  }

  /**
   * Indicates whether the current game has finished
   * 
   * @return true if there is a winner or the board is full
   */
  public boolean gameOver() {

    if (this.getWinner() != EMPTY || this.isBoardFull())
      return true;
    else
      return false;
  }

  /**
   * Copies the current Connect4GameState instance into another object
   * 
   * @return the new Connect4GameState instance
   */
  public Connect4GameState copy() {
    // Create a new game state
    MyGameState copyState = new MyGameState();
    // Copy each of the element of the board to the new game state
    for (int i = 0; i < NUM_ROWS; i++) {
      for (int j = 0; j < NUM_COLS; j++) {
        copyState.board[i][j] = this.board[i][j];
      }
    }
    // Copy the value of the currentTurn instance variable
    copyState.currentTurn = this.currentTurn;

    return copyState;

  }
}
