package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import jmhao.engine.UIElement;
import jmhao.game.Final;
/**
 * QuitGameBar for menu screen
 * @author jmhao
 *
 */
public class QuitGameBar extends UIElement {
  private float xFactor = .25f;
  private float yFactor = .6f;
  private float widthFactor = .5f;
  private float heightFactor = .2f;
  private Vec2f coords;
  private Vec2f sides;
  private boolean selected = false;
  private Rectangle2D box;
  public QuitGameBar(Vec2i startPoint) {
    coords = new Vec2f(startPoint.x * xFactor, startPoint.y * yFactor);
    sides = new Vec2f(startPoint.x * widthFactor, startPoint.y * heightFactor);
    box = new Rectangle2D.Float(coords.x, coords.y, sides.x, sides.y);
  }
  @Override
  public void setSize(Vec2i newSize) {
    coords = new Vec2f(newSize.x * xFactor, newSize.y * yFactor);
    sides = new Vec2f(newSize.x * widthFactor, newSize.y * heightFactor);
    box = new Rectangle2D.Float(coords.x, coords.y, sides.x, sides.y);
  }

  @Override
  public void drawElement(Graphics2D g) {
    if(selected) {
      g.setColor(Color.green);
      g.fill(box);
    }
    g.setColor(Color.black);
    g.draw(box);
    g.drawImage(Final.borderPng, (int)(box.getMinX()*.98), (int)(box.getMinY()*.96), (int)(box.getMaxX()*1.01), (int)(box.getMaxY()*1.02), 0, 0, 1000, 500, null);
    
    int fontSize = (int)(coords.x/(xFactor*50));
    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
    g.drawString("Quit", (int)(coords.x + sides.x * .2), (int)(coords.y + sides.y * .5));
    g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
  }

  @Override
  public boolean isSelected(Point p) {
    return box.contains(p);
  }
  @Override
  public void setSelected(boolean s) {
    selected = s;
  }

}