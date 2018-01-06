package assignment2017;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;

/**
 * RandomPlayer class Implements the functionality of a random player
 */
import java.lang.Math;

public class RandomPlayer extends Connect4Player {

  /**
   * Decides which column in which to move, and then calls move on the gameState instance in order
   * to make the move. The method is free to query gameState (i.e. through the getCounterAt method
   * in order to decide which move to make)
   * 
   * @param gameState the current Connect4 game state
   */
  public void makeMove(Connect4GameState gameState) {

    int randomColumn;
    // Get a random column number in the range 0-6
    // If the column is full get another number
    do {
      randomColumn = (int) (Math.random() * Connect4GameState.NUM_COLS);

    } while (gameState.isColumnFull(randomColumn));

    // Make the move
    try {
      gameState.move(randomColumn);
      System.out.println("Computer dropped counter in column " + randomColumn);
    } catch (IllegalColumnException e) {
      System.out.println("Invalid column number chosen by computer! ");
    } catch (ColumnFullException e) {
      System.out.println("Column is full!");
    }
  }
}
