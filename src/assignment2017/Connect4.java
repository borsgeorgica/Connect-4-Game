package assignment2017;

import javax.swing.JLabel;

import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;

/**
 * Connect4 class. It has a Connect4GameState instance, passing this instance to each player object,
 * allowing it to make moves. It also has a display instance, which allows it to print the board on
 * the console.
 * 
 * @author Georgica Bors
 *
 */
public class Connect4 {

  // Instance variables
  private final Connect4GameState gameState;
  private final Connect4Player red;
  private final Connect4Player yellow;
  private final Connect4Displayable display;
  private JLabel outputBox;
  private boolean outputBoxUsed = false;

  /**
   * Constructor
   * 
   * @param gameState the game state
   * @param red the red player object
   * @param yellow the yellow player object
   * @param display the display instance
   */
  public Connect4(Connect4GameState gameState, Connect4Player red, Connect4Player yellow,
      Connect4Displayable display) {
    this.gameState = gameState;
    this.red = red;
    this.yellow = yellow;
    this.display = display;
  }

  /**
   * Constructor
   * 
   * @param gameState the game state
   * @param red the red player object
   * @param yellow the yellow player object
   * @param display the display instance
   * @param outputBox the outputBox on the screen
   */

  public Connect4(Connect4GameState gameState, Connect4Player red, Connect4Player yellow,
      Connect4Displayable display, JLabel outputBox) {
    this(gameState, red, yellow, display);
    this.outputBox = outputBox;
    outputBoxUsed = true;
  }

  /**
   * Each player makes a move in turn until the game is over. The board is displayed after each turn
   */
  public void play() {
    // Initialize the game
    gameState.startGame();

    // Each player makes a move until the game is over
    // Either one of the players has won or the board is full
    do {
      // Display board
      display.displayBoard();

      if (gameState.whoseTurn() == Connect4GameState.RED) {
        // Display whose turn first
        if (outputBoxUsed)
          outputBox.setText("Computer's turn.");
        red.makeMove(gameState);
      }

      else {
        // Display whose turn first
        if (outputBoxUsed)
          outputBox.setText("Your turn. Choose column by clicking on it.");
        yellow.makeMove(gameState);
      }


    } while (!gameState.gameOver());

    display.displayBoard();
    // Check who is the winner
    int winner = gameState.getWinner();
    if (winner == Connect4GameState.YELLOW) {

      if (outputBoxUsed)
        outputBox.setText("Y wins. Press start button to play again.");
      else
        System.out.println("Y wins");
    }

    else if (winner == Connect4GameState.RED) {
      if (outputBoxUsed)
        outputBox.setText("R wins. Press start button to play again.");
      else
        System.out.println("R wins");
    }

    else {
      if (outputBoxUsed)
        outputBox.setText("There is no winner!");
      else
        System.out.println("There is no winner!");
    }

  }

}
