package jmhao.engine;

import java.awt.Graphics2D;
import java.awt.Point;

import cs195n.Vec2i;
/**
 * UIElement abstract class for all UIElements
 * @author jmhao
 *
 */
public abstract class UIElement {
  public abstract void setSize(Vec2i newSize);
  public abstract void drawElement(Graphics2D g);
  public abstract boolean isSelected(Point p);
  public abstract void setSelected(boolean s);
}
