package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import jmhao.engine.UIElement;
import jmhao.engine.Sound.ClipPlayer;
import jmhao.game.Final;

public class MusicPage extends UIElement {
  private Vec2f coords, sides;
  private Vec2f sideFactor = new Vec2f(.3f, .5f);
  private Vec2f posFactor = new Vec2f(.3f, .48f);
  private boolean selected = false;
  private ArrayList<String> songs = new ArrayList<String>();
  private ArrayList<Rectangle2D> boxes = new ArrayList<Rectangle2D>();
  private Rectangle2D largeBox;
  public MusicPage(Vec2i newSize) {
    songs = new ArrayList<String>();
    songs.add("warriors");
    songs.add("shelter");
    songs.add("itGMa");
    songs.add("seeYou");
    songs.add("trndsttr");
    songs.add("whispy");
    songs.add("wonderfulLife");
    coords = new Vec2f(newSize.x*posFactor.x, newSize.y*posFactor.y);
    sides = new Vec2f(newSize.x*sideFactor.x, newSize.y*sideFactor.y);
    largeBox = new Rectangle2D.Float(coords.x, coords.y, sides.x, sides.y);
    float height = sides.y/songs.size();
    Vec2f increment = new Vec2f(sides.x, height);
    Vec2f curr = coords;
    for(int i = 0; i < songs.size(); i++) {
      Rectangle2D box = new Rectangle2D.Float(curr.x, curr.y, increment.x, increment.y);
      boxes.add(box);
      curr = curr.plus(new Vec2f(0, height));
    }
  }
  @Override
  public void setSize(Vec2i newSize) {
    coords = new Vec2f(newSize.x*posFactor.x, newSize.y*posFactor.y);
    sides = new Vec2f(newSize.x*sideFactor.x, newSize.y*sideFactor.y);
    largeBox = new Rectangle2D.Float(coords.x, coords.y, sides.x, sides.y);
    float height = sides.y/songs.size();
    Vec2f increment = new Vec2f(sides.x, height);
    Vec2f curr = coords;
    boxes = new ArrayList<Rectangle2D>();
    for(int i = 0; i < songs.size(); i++) {
      Rectangle2D box = new Rectangle2D.Float(curr.x, curr.y, increment.x, increment.y);
      boxes.add(box);
      curr = curr.plus(new Vec2f(0, height));
    }
  }

  @Override
  public void drawElement(Graphics2D g) {
    Final.leagueFont = Final.leagueFont.deriveFont(Font.PLAIN,14);
    g.setFont(Final.leagueFont);
    g.draw(largeBox);
    g.setColor(Color.black);
    g.drawString("Play songs!", (int)largeBox.getCenterX(), (int)largeBox.getMinY() - 10);
    for(int i = 0; i < boxes.size(); i++) {
      Rectangle2D r = boxes.get(i);
      String s = songs.get(i);
      g.setColor(new Color(29,52,55));
      g.fill(r);
      g.setColor(new Color(255,215,10));
      g.draw(r);
      g.drawString(s, (int)(r.getMinX() + 10), (int)(r.getCenterY()));
    }
    g.drawImage(Final.borderPng, (int)(largeBox.getMinX()*.98), (int)(largeBox.getMinY()*.96), (int)(largeBox.getMaxX()*1.01), (int)(largeBox.getMaxY()*1.02), 0, 0, 1000, 500, null);
    
  }

  @Override
  public boolean isSelected(Point p) {
    // TODO Auto-generated method stub
    return false;
  }
  public void checkClick(Point p) {
    for(int i = 0; i < boxes.size(); i++) {
      Rectangle2D r = boxes.get(i);
      if(r.contains(p)) {
        String s = songs.get(i);
        ClipPlayer.playClip(s);
        break;
      }
    }
  }

  @Override
  public void setSelected(boolean s) {
    // TODO Auto-generated method stub
    
  }

}
