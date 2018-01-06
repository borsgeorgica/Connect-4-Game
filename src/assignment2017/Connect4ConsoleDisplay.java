package assignment2017;

import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;

/**
 * Connect4ConsoleDisplay class Implements the Connect4Displayable interface
 * 
 * @author Georgica Bors
 *
 */
public class Connect4ConsoleDisplay implements Connect4Displayable {

  private final Connect4GameState gameState;

  // Using an enum for the colour
  private enum colour {
    R, Y
  };

  /**
   * Constructor
   * 
   * @param gameState the instance of the game state
   */
  public Connect4ConsoleDisplay(Connect4GameState gameState) {
    this.gameState = gameState;
  }

  /**
   * Displays the board on the console
   */
  public void displayBoard() {
    int colourConst;
    for (int i = MyGameState.NUM_ROWS - 1; i >= 0; i--) {
      System.out.print("| ");
      for (int j = 0; j < MyGameState.NUM_COLS; j++) {
        colourConst = gameState.getCounterAt(j, i);

        switch (colourConst) {

          case 1:
            System.out.print(colour.Y + " ");
            break;
          case 0:
            System.out.print(colour.R + " ");
            break;

          default:
            System.out.print("  ");
            break;
        }

      }
      System.out.print("|");
      System.out.println();
    }
    System.out.print(" ");
    for (int i = 1; i <= 2 * MyGameState.NUM_COLS + 1; i++) {
      System.out.print("-");
    }
    System.out.println();
    System.out.print("  ");
    for (int i = 0; i < MyGameState.NUM_COLS; i++) {
      System.out.print(i + " ");
    }
    System.out.println();

  }

}
