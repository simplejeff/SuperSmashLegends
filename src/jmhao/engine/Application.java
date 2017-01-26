package jmhao.engine;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import cs195n.SwingFrontEnd;
import cs195n.Vec2i;

/**
 * Application class for engine side to handle screen forwarding
 * @author jmhao
 *
 */
public class Application extends SwingFrontEnd{
  Screen currentScreen;
  /**
   * Constructor for Application
   * @param title string title of our window
   * @param fullscreen boolean of whether to display fullscreen
   */
  public Application(String title, boolean fullscreen) {
    super(title, fullscreen);
  }
  /**
   * setScreen sets our current screen
   * @param topScreen screen to set current to
   */
  public void setScreen(Screen topScreen) {
    currentScreen = topScreen;
  }
  
  @Override
  protected void onTick(long nanosSincePreviousTick) {
    currentScreen.onTick(nanosSincePreviousTick);
  }

  @Override
  protected void onDraw(Graphics2D g) {
    currentScreen.onDraw(g);
    
  }

  @Override
  protected void onKeyTyped(KeyEvent e) {
    currentScreen.onKeyTyped(e);
  }

  @Override
  protected void onKeyPressed(KeyEvent e) {
    currentScreen.onKeyPressed(e);
  }

  @Override
  protected void onKeyReleased(KeyEvent e) {
    currentScreen.onKeyReleased(e);
  }

  @Override
  protected void onMouseClicked(MouseEvent e) {
    currentScreen.onMouseClicked(e);
  }

  @Override
  protected void onMousePressed(MouseEvent e) {
    currentScreen.onMousePressed(e);
  }

  @Override
  protected void onMouseReleased(MouseEvent e) {
    currentScreen.onMouseReleased(e);
  }

  @Override
  protected void onMouseDragged(MouseEvent e) {
    currentScreen.onMouseDragged(e);
  }

  @Override
  protected void onMouseMoved(MouseEvent e) {
    currentScreen.onMouseMoved(e);
  }

  @Override
  protected void onMouseWheelMoved(MouseWheelEvent e) {
    currentScreen.onMouseWheelMoved(e);
  }

  @Override
  protected void onResize(Vec2i newSize) {
    currentScreen.onResize(newSize);
  }

}
