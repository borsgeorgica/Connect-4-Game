package assignment2017;

import assignment2017.codeprovided.ColumnFullException;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;
import assignment2017.codeprovided.IllegalColumnException;

/**
 * IntelligentPlayer class Implements implements an intelligent player with 3 moves ahead
 * 
 * @author Georgica Bors
 *
 */

public class IntelligentPlayer extends Connect4Player {

  private int whoseTurn;
  private final int NUM_ROWS = Connect4GameState.NUM_ROWS;
  private final int NUM_COLS = Connect4GameState.NUM_COLS;
  private int columnToChoose;
  private final int LEVEL = 5;
  private final int WIN_SCORE = 100;


  public void makeMove(Connect4GameState gameState) {
    this.whoseTurn = gameState.whoseTurn();
    int bestColumn;
    // First check if opponent may win in next move
    if (checkOpponentWinState(gameState))
      bestColumn = columnToChoose;
    else
      // Look ahead 3 moves and choose the move with highest chance of win
      bestColumn = getBestColumn(gameState, LEVEL);

    // Make move
    try {
      gameState.move(bestColumn);
    } catch (IllegalColumnException e) {

      e.printStackTrace();
    } catch (ColumnFullException e) {

      e.printStackTrace();
    }
  }

  // get best move
  // Recursive function
  private int getBestColumn(Connect4GameState gameState, int level) {

    int bestScore = 0;
    int bestColumn = 0;
    // If got to level 0, stop there and start working back the best move
    if (level == 0) {
      return 0;
    }

    else {
      for (int col = 0; col < Connect4GameState.NUM_COLS; col++) {

        if (gameState.getCounterAt(col,
            Connect4GameState.NUM_ROWS - 1) == Connect4GameState.EMPTY) {
          int currentScore = 0;
          // Create a copy of the board
          Connect4GameState boardCopy = gameState.copy();
          // Make a move for each column
          try {
            boardCopy.move(col);
          } catch (IllegalColumnException e) {

            e.printStackTrace();
          } catch (ColumnFullException e) {

            e.printStackTrace();
          }

          // Check if there is a win
          if (boardCopy.getWinner() == this.whoseTurn) {
            currentScore = currentScore + (level * WIN_SCORE);
          } else if (boardCopy.getWinner() == Connect4GameState.EMPTY) {
            // go ahead
            // calculate local score
            currentScore = currentScore + calculateLocalScore(boardCopy);
            // Call the same method recursively
            currentScore = currentScore + getBestColumn(boardCopy, level - 1);
          } else {
            currentScore = currentScore - (level * WIN_SCORE);
          }

          if (currentScore > bestScore) {
            bestScore = currentScore;
            bestColumn = col;
          }
        }
      }
      return bestColumn;
    }

  }

  // Calculate the score of a game state
  private int calculateLocalScore(Connect4GameState gameState) {
    int bestScore = 0;
    int currentScore = 0;

    for (int i = 0; i < NUM_ROWS; i++) {
      for (int j = 0; j < NUM_COLS; j++) {
        if (gameState.getCounterAt(j, i) == Connect4GameState.EMPTY) {
          currentScore += getScoreUp(j, i, gameState);

          currentScore += getScoreDown(j, i, gameState);

          currentScore += getScoreLeft(j, i, gameState);

          currentScore += getScoreRight(j, i, gameState);

          currentScore += getScoreForwardSlash(j, i, gameState);

          currentScore += getScoreBackSlash(j, i, gameState);

          if (currentScore > bestScore) {
            bestScore = currentScore;
          }
        }
        currentScore = 0;
      }
    }
    return bestScore;
  }

  // Get score up
  private int getScoreUp(int column, int row, Connect4GameState gameState) {
    int counter = 0;
    for (int i = 1; i <= 3; i++) {
      if (row + i < NUM_ROWS) {
        if (gameState.getCounterAt(column, row + i) == Connect4GameState.EMPTY)
          counter += 5;
        else
          return counter;
      }
    }
    return counter;
  }

  // check right
  private int getScoreDown(int column, int row, Connect4GameState gameState) {
    int counter = 0;
    for (int i = 1; i <= 3; i++) {
      if (row - i >= 0) {
        if (gameState.getCounterAt(column, row - i) == gameState.whoseTurn())
          counter += 10;
        else
          return counter;
      }
    }
    return counter;
  }

  // check down
  private int getScoreLeft(int column, int row, Connect4GameState gameState) {
    int counter = 0;
    for (int i = 1; i <= 3; i++) {
      if (column - i >= 0) {
        if (gameState.getCounterAt(column - i, row) == gameState.whoseTurn())
          counter += 10;
        else
          return counter;
      }
    }
    return counter;
  }

  // check left
  private int getScoreRight(int column, int row, Connect4GameState gameState) {
    int counter = 0;
    for (int i = 1; i <= 3; i++) {
      if (column + i < NUM_COLS) {
        if (gameState.getCounterAt(column + i, row) == gameState.whoseTurn())
          counter += 10;
        else
          return counter;
      }
    }
    return counter;
  }

  // Get score forward slash
  private int getScoreForwardSlash(int column, int row, Connect4GameState gameState) {
    int counter = 0;
    for (int i = 1; i <= 3; i++) {
      if (column + i < NUM_COLS && row + i < NUM_ROWS) {
        if (gameState.getCounterAt(column + i, row + i) == gameState.whoseTurn())
          counter += 10;
        else
          return counter;
      }
    }
    return counter;
  }

  // Get score backslash
  private int getScoreBackSlash(int column, int row, Connect4GameState gameState) {
    int counter = 0;
    for (int i = 1; i <= 3; i++) {
      if (column + i < NUM_COLS && row - i >= 0) {
        if (gameState.getCounterAt(column + i, row - i) == gameState.whoseTurn())
          counter += 10;
        else
          return counter;
      }
    }
    return counter;
  }

  // Check if the oponent is able to win on next move
  private boolean checkOpponentWinState(Connect4GameState gameState) {
    int winner;

    for (int i = 0; i < NUM_COLS; i++) {
      Connect4GameState gameStateCopy = gameState.copy();
      if (gameStateCopy.whoseTurn() == Connect4GameState.RED) {
        ((MyGameState) gameStateCopy).currentTurn = Connect4GameState.YELLOW;
      }

      else {
        ((MyGameState) gameStateCopy).currentTurn = Connect4GameState.RED;
      }

      if (gameStateCopy.getCounterAt(i, NUM_ROWS - 1) == Connect4GameState.EMPTY) {
        // make a move, then check if there is a winning state
        try {
          gameStateCopy.move(i);
        } catch (IllegalColumnException | ColumnFullException e) {
          e.printStackTrace();
        }
        // check if winning state
        winner = gameStateCopy.getWinner();

        if (gameState.whoseTurn() == Connect4GameState.RED) {
          if (winner == Connect4GameState.YELLOW) {
            columnToChoose = i;
            return true;
          }
        }
      }
    }
    return false;
  }
}
