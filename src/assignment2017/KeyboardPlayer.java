package assignment2017;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;
import sheffield.EasyReader;

/**
 * KeyboardPlayer class Implements the functionality of a keyboard player
 * 
 * @author Georgica Bors
 *
 */

public class KeyboardPlayer extends Connect4Player {

  /**
   * Decides which column in which to move, and then calls move on the gameState instance in order
   * to make the move. The method is free to query gameState (i.e. through the getCounterAt method
   * in order to decide which move to make)
   * 
   * @param gameState the current Connect4 game state
   */
  public void makeMove(Connect4GameState gameState) {
    // Create an EasyReader instance to use for the input of the user
    EasyReader keyboard = new EasyReader();
    int col;
    // Exception handling for the input from the user
    try {
      System.out.println("Please enter a column number, 0 to 6 followed by return.");
      col = Integer.parseInt(keyboard.readString());
      try {
        // Try to make the move
        gameState.move(col);
      } catch (IllegalColumnException e) {

        System.out.println(e.getMessage());

      } catch (ColumnFullException e) {

        System.out.println(e.getMessage());
      }
    } catch (NumberFormatException e) {
      // The input was not an integer
      System.out.println("That was not an integer!");
    }
  }
}
