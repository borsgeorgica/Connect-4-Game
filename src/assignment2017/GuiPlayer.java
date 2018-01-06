package assignment2017;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;

/**
 * GuiPlayer class. Implements the functionality of a GUI player
 * 
 * 
 * @author Georgica Bors
 *
 */
public class GuiPlayer extends Connect4Player {

  /**
   * Decides which column in which to move, and then calls move on the gameState instance in order
   * to make the move. The method is free to query gameState (i.e. through the getCounterAt method
   * in order to decide which move to make)
   * 
   * @param gameState the current Connect4 game state
   */

  private final MyButton[][] counters;
  private boolean canMove;
  private int column;

  public GuiPlayer(MyButton[][] counters) {
    this.counters = counters;
    // Add an event listener to each button
    for (int i = 0; i < Connect4GameState.NUM_ROWS; i++) {
      for (int j = 0; j < Connect4GameState.NUM_COLS; j++) {
        this.counters[i][j].addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            // find the column
            for (int i = 0; i < Connect4GameState.NUM_ROWS; i++) {
              for (int j = 0; j < Connect4GameState.NUM_COLS; j++) {
                if (counters[i][j] == e.getSource()) {
                  column = j;
                  canMove = true;
                }
              }
            }
          }
        });
      }
    }
  }

  public void makeMove(Connect4GameState gameState) {

    canMove = false;

    while (!canMove) {
      // Display on the screen whose turn it is
      // Wait for the user input
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } ;
    }

    // make move
    // drop counter at chosen column
    try {
      gameState.move(column);
    } catch (IllegalColumnException e1) {
      e1.printStackTrace();
    } catch (ColumnFullException e1) {

      e1.printStackTrace();
    }
  }
}
