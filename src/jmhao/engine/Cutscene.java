package jmhao.engine;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import cs195n.Vec2f;
import cs195n.Vec2i;
import jmhao.engine.UIElement;

public class Cutscene extends UIElement {
  protected float xFactor = .4f;
  private float yFactor = .5f;
  private float widthFactor = .6f;
  private float boxWidthFactor = .15f;
  private float heightFactor = .15f;
  protected Vec2f coords;
  protected Vec2f sides;
  protected Vec2f boxSides;
  protected Rectangle2D box;
  protected Rectangle2D textBox;
  boolean start = false;
  boolean finish = false;
  boolean selected = false;
  long charCounter = 0;
  int current = 0;
  String quote;
  String currQuote = "";
  public Cutscene(Vec2i startPoint, float x, float y, String quote) {
    xFactor = x;
    yFactor = y;
    this.quote = quote;
    coords = new Vec2f(startPoint.x * xFactor, startPoint.y * yFactor);
    sides = new Vec2f(startPoint.x * widthFactor, startPoint.x * heightFactor);
    boxSides = new Vec2f(startPoint.x*boxWidthFactor, startPoint.x * heightFactor);
    box = new Rectangle2D.Float(coords.x, coords.y, boxSides.x, boxSides.y);
    textBox = new Rectangle2D.Float(coords.x+boxSides.x, coords.y, sides.x, sides.y);
  }
  public void setStart() {
    start = true;
  }
  @Override
  public void setSize(Vec2i newSize) {
    coords = new Vec2f(newSize.x * xFactor, newSize.y * yFactor);
    sides = new Vec2f(newSize.x * widthFactor, newSize.x * heightFactor);
    boxSides = new Vec2f(newSize.x*boxWidthFactor, newSize.x * heightFactor);
    box = new Rectangle2D.Float(coords.x, coords.y, boxSides.x, boxSides.y);
    textBox = new Rectangle2D.Float(coords.x+boxSides.x, coords.y, sides.x, sides.y);
  }

  @Override
  public void drawElement(Graphics2D g) {
    g.setColor(Color.white);
    g.fill(textBox);
    g.setColor(Color.red);
    if(finish) {
      g.setColor(Color.green);
    }
    g.draw(box);
    g.draw(textBox);
    g.setColor(Color.black);
    int fontSize = (int)(coords.x/(xFactor*60));
    Font f = g.getFont();
    f = f.deriveFont(Font.PLAIN, fontSize);
    //g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
    g.drawString(currQuote, (int)(textBox.getMinX()*1.2), (int)(coords.y + sides.y * .2));
    //g.drawString(whichPlayer, (int)(coords.x + sides.x * .2), (int)(coords.y - sides.y * .5));
    f = g.getFont();
    f = f.deriveFont(Font.PLAIN, 12);
    //g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
  }
  public void onTick(long millisSincePrevTick) {
    if(start) {
      charCounter += millisSincePrevTick;
      current = (int)charCounter/100;
      if(current > quote.length()) {
        finish = true;
        current = quote.length();
      }
      currQuote = quote.substring(0, current);
    }
  }
  public void start() {
    start = true;
  }
  public boolean isFinished() {
    return finish;
  }
  public boolean isStarted() {
    return start;
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
