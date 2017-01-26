package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import jmhao.engine.World.Message;

public class ReloadMessage extends Message{
  private String message;
  private Vec2f coords;
  public ReloadMessage(Vec2f startPoint, String s) {
    coords = startPoint;
    message = s;
  }
  @Override
  public void setDimensions(float x, float y) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setMessage(String m) {
    message = m;
  }

  @Override
  public void setSize(Vec2i newSize) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void drawElement(Graphics2D g) {
    g.setColor(Color.black);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
    g.drawString(message, coords.x, coords.y);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
  }

  @Override
  public boolean isSelected(Point p) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setSelected(boolean s) {
    // TODO Auto-generated method stub
    
  }

}
