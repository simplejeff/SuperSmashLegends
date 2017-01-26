package jmhao.engine.World;

import jmhao.engine.UIElement;


public abstract class Message extends UIElement{
  /**
   * Sets the dimensions relative to the screen for the message
   * @param x dimension
   * @param y dimension
   */
  public abstract void setDimensions(float x, float y);
  /**
   * Sets the message string
   * @param m string to set message to
   */
  public abstract void setMessage(String m);
}
