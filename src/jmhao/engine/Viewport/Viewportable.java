package jmhao.engine.Viewport;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

public interface Viewportable {
  public void drawElement(Graphics2D g);
  /**
   * returns width of board
   * @return width
   */
  public int getWidth();
  /**
   * returns length of board
   * @return length
   */
  public int getLength();
  /**
   * Sets the selected
   * @param p
   */
  public void setSelected(Point p);
  /**
   * Get millis since prev tick
   * @param millisSincePrevTick millis since prev tick
   */
  public void getTick(long millisSincePrevTick);
  /**
   * keyPressed gets a keyPress event
   * @param e keypress event
   */
  public void keyPressed(KeyEvent e);
}
