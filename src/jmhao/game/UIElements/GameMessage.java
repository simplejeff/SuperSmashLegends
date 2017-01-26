package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;

import jmhao.engine.World.Message;
import jmhao.game.Final;
/**
 * Message class to handle string UIElements
 * @author jmhao
 *
 */
public class GameMessage extends Message{
  private float xFactor = .3f;
  private float yFactor = .2f;
  private Vec2f coords;
  private String message;
  public GameMessage(Vec2i startPoint, String s) {
    coords = new Vec2f(startPoint.x * xFactor, startPoint.y * yFactor);
    message = s;
  }
  @Override
  public void setSize(Vec2i newSize) {
    coords = new Vec2f(newSize.x * xFactor, newSize.y * yFactor);
  }

  @Override
  public void drawElement(Graphics2D g) {
    g.setColor(new Color(29,52,55));
    g.fillRect(0, (int)coords.y-50, 2000, 75);
    g.drawImage(Final.borderPng, -50, (int)coords.y-50, 2000, (int)coords.y-50+75, 0, 0, 1000, 500, null);
    int fontSize = (int)(coords.x/(xFactor*47));
    g.setColor(new Color(255,215,0));
    Final.leagueFont = Final.leagueFont.deriveFont(Font.PLAIN,fontSize);
    g.setFont(Final.leagueFont);
   // g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
    g.drawString(message, coords.x, coords.y);
    Final.leagueFont = Final.leagueFont.deriveFont(Font.PLAIN,12);
    g.setFont(Final.leagueFont);
  }
  //This is never used for message
  @Override
  public boolean isSelected(Point p) {
    // TODO Auto-generated method stub
    return false;
  }
  @Override
  public void setSelected(boolean s) {
  }
  @Override
  public void setDimensions(float x, float y) {
    float tempX = coords.x/xFactor;
    float tempY = coords.y/yFactor;
    xFactor = x;
    yFactor = y;
    coords = new Vec2f(tempX * xFactor, tempY * yFactor);
  }
  @Override
  public void setMessage(String m) {
    message = m;
  }

}
