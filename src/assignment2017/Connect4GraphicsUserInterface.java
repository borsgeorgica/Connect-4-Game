package assignment2017;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.*;

import assignment2017.codeprovided.Connect4Displayable;
import assignment2017.codeprovided.Connect4GameState;

/**
 * Connect4GraphicsUserInterface class. It uses a JFrame to display the gameboard on the screen
 * 
 * 
 * @author Georgica Bors
 *
 */
@SuppressWarnings("serial")
public class Connect4GraphicsUserInterface extends JFrame implements Connect4Displayable {
  // Instance variables
  private final Connect4GameState gameState;
  private final MyButton[][] counters;
  private final JLabel info = new JLabel("This will be the output...");
  private final JButton startGame = new JButton("START GAME");
  private final JComboBox<String> typeOfPlayer = new JComboBox<>();
  private final int WINDOW_WIDTH = 700;
  private final int WINDOW_HEIGHT = 500;

  public Connect4GraphicsUserInterface(Connect4GameState gameState) {
    this.gameState = gameState;

    // Set up the window
    setTitle("Connect 4");
    Toolkit toolkit = Toolkit.getDefaultToolkit();

    Dimension screenDimensions = toolkit.getScreenSize();
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

    setLocation(new Point(screenDimensions.width / 4, screenDimensions.height / 4));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create a Jpanel for the board
    JPanel boardPanel = new JPanel();
    // Create another panel for each sqaure on the board
    JPanel buttonPanel = new JPanel();
    // Populate the ComboBox
    typeOfPlayer.addItem("Random Player");
    typeOfPlayer.addItem("Intelligent Player");

    buttonPanel.add(typeOfPlayer);
    buttonPanel.setBackground(Color.WHITE);
    // Window settings
    this.info.setFont(new Font("Cracked", Font.ITALIC, 25));
    this.info.setBackground(Color.BLACK);
    this.info.setForeground(Color.BLUE);

    // Set up the board panel
    boardPanel.setBackground(Color.cyan);
    boardPanel.setLayout(new GridLayout(6, 7));
    Dimension prefSize = new Dimension(600, 300);
    boardPanel.setPreferredSize(prefSize);
    buttonPanel.setBackground(Color.GRAY);

    // create an array of panels for each counter
    counters = new MyButton[6][7];
    // Add a panel for each counter and add it to the board
    int x, y;
    int d = 40;
    Color backgroundColour = new Color(13, 48, 104);
    Color circleColour = new Color(141, 181, 244);

    for (int i = 5; i >= 0; i--) {
      for (int j = 0; j < Connect4GameState.NUM_COLS; j++) {
        x = 10;
        y = 10;

        counters[i][j] = new MyButton(backgroundColour, circleColour, x, y, d);
        boardPanel.add(counters[i][j]);

      }
    }
    buttonPanel.add(startGame);
    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());
    contentPane.add(boardPanel, BorderLayout.CENTER);
    contentPane.add(this.info, BorderLayout.NORTH);
    contentPane.add(buttonPanel, BorderLayout.WEST);
    setVisible(true);
  }

  public void displayBoard() {
    // display the board on the window
    setVisible(true);

    int counterColour;

    Color circleColour = new Color(141, 181, 244);
    // Update the colour of each counter on the board
    for (int i = 0; i < Connect4GameState.NUM_ROWS; i++) {
      for (int j = 0; j < Connect4GameState.NUM_COLS; j++) {

        counterColour = this.gameState.getCounterAt(j, i);

        switch (counterColour) {
          case -1:
            circleColour = new Color(141, 181, 244);
            break;
          case 0:
            circleColour = Color.red;
            break;
          case 1:
            circleColour = Color.yellow;
            break;
        }
        this.counters[i][j].setCircleColour(circleColour);
      }
    }
    this.repaint();
  }

  // Getter methods
  public MyButton[][] getCounters() {
    return this.counters;
  }

  public JButton getStartButton() {
    return this.startGame;
  }

  public JComboBox<String> getTypeOfPlayer() {
    return this.typeOfPlayer;
  }

  public void outputInfo(String text) {
    this.info.setText(text);
  }

  public JLabel getOutputBox() {
    return this.info;
  }
}
