package assignment2017;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

/**
 * MyButton class extends JButton - used for each counter
 * 
 * @author Georgica Bors
 *
 */
@SuppressWarnings("serial")
public class MyButton extends JButton {
  private int x;
  private int y;
  private int diameter;
  private Color circleColour;

  public MyButton(Color colour, Color circleColour, int x, int y, int d) {
    setBackground(colour);
    this.x = x;
    this.y = y;
    this.diameter = d;
    this.circleColour = circleColour;
  }

  public void setCircleColour(Color circleColour) {
    this.circleColour = circleColour;
  }

  // Override the paintComponent method
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
    g2.setStroke(new BasicStroke(5));

    g2.draw(circle);
    g2.setColor(circleColour);
    g2.fill(circle);

  }
}
