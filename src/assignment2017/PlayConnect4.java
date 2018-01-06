package assignment2017;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;
import assignment2017.codeprovided.Connect4Player;

/**
 * PlayConnect4 class Starts the game
 * 
 * @author Georgica Bors
 *
 */
public class PlayConnect4 {


  public static void main(String[] args) {

    // Create a new game state object
    Connect4GameState gameState = new MyGameState();
    Connect4Displayable display;
    String typeOfPlayer;
    Connect4Player red;
    Connect4Player yellow;
    Connect4 game;
    final boolean gameOn = true;

    if (args[0].equals("-gui")) {
      // Start a GUI game
      display = new Connect4GraphicsUserInterface(gameState);
      yellow = new GuiPlayer(((Connect4GraphicsUserInterface) display).getCounters());
      // Get the controls from the screen
      JButton startGame = ((Connect4GraphicsUserInterface) display).getStartButton();
      JComboBox<String> comboBox = ((Connect4GraphicsUserInterface) display).getTypeOfPlayer();
      JLabel outputBox = ((Connect4GraphicsUserInterface) display).getOutputBox();
      // Add an action listener to the button
      startGame.addActionListener(new ButtonHandler());

      while (gameOn) {
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        while (!ButtonHandler.canPlay) {
          // wait for the start button
          try {
            Thread.sleep(50);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

        }
        // Before starting the game, check what type of player
        typeOfPlayer = (String) comboBox.getSelectedItem();

        if (typeOfPlayer.equals("Random Player"))
          red = new RandomPlayer();

        else if (typeOfPlayer.equals("Intelligent Player"))
          red = new IntelligentPlayer();

        else {
          red = new RandomPlayer();
        }

        game = new Connect4(gameState, red, yellow, display, outputBox);
        game.play();
        ButtonHandler.canPlay = false;
      }

    } else if (args[0].equals("-nogui")) {
      // Start a console display game
      display = new Connect4ConsoleDisplay(gameState);
      // Create the players
      red = new RandomPlayer();
      yellow = new KeyboardPlayer();

      game = new Connect4(gameState, red, yellow, display);
      // Start the game
      game.play();
    }
  }
}


// Button Handler for Start Button
// When the button is pressed the game is started
class ButtonHandler implements ActionListener {

  public static boolean canPlay = false;

  @Override
  public void actionPerformed(ActionEvent e) {

    canPlay = true;
  }
}
