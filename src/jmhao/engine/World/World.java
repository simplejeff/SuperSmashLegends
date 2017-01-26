package jmhao.engine.World;

import cs195n.Vec2i;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import jmhao.engine.Entity.Entity;

public abstract class World {

  /**
   * @param e   an AWT {@link KeyEvent} representing the input event.
   * @see KeyListener#keyPressed(KeyEvent)
   */
  protected abstract void onKeyPressed(KeyEvent e);
  /**
   * @param e   an AWT {@link KeyEvent} representing the input event.
   * @see KeyListener#keyReleased(KeyEvent)
   */
  protected abstract void onKeyReleased(KeyEvent e);
  /**
   * draws element
   * @param g graphics2d to utilize
   */
  public abstract void drawElement(Graphics2D g);

  /**
   * @param e   an AWT {@link MouseEvent} representing the input event.
   * @see MouseListener#mouseClicked(MouseEvent)
   */
  protected abstract void onMouseClicked(MouseEvent e);
  
  /**
   * @param e   an AWT {@link MouseEvent} representing the input event.
   * @see MouseListener#mousePressed(MouseEvent)
   */
  protected abstract void onMousePressed(MouseEvent e);
  
  /**
   * @param e   an AWT {@link MouseEvent} representing the input event.
   * @see MouseListener#mouseReleased(MouseEvent)
   */
  protected abstract void onMouseReleased(MouseEvent e);
  
  /**
   * @param e   an AWT {@link MouseEvent} representing the input event.
   * @see MouseMotionListener#mouseDragged(MouseEvent)
   */
  protected abstract void onMouseDragged(MouseEvent e);
  
  /**
   * @param e   an AWT {@link MouseEvent} representing the input event.
   * @see MouseMotionListener#mouseMoved(MouseEvent)
   */
  protected abstract void onMouseMoved(MouseEvent e);
  /**
   * Called when the size of the drawing area changes. Any subsequent calls to onDraw should note
   * the new size and be sure to fill the entire area appropriately. Guaranteed to be called
   * before the first call to onDraw.
   * 
   * @param newSize the new size of the drawing area.
   */
  protected abstract void onResize(Vec2i newSize);
}
