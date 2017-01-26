package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.InputStream;

import jmhao.engine.UIElement;
import jmhao.engine.Entity.Facing;
import jmhao.game.Final;
/**
 * startGameBar for the first map
 * @author jmhao
 *
 */
public class CharacterSelect extends UIElement {
  private float xFactor = .05f;
  private float yFactor = .4f;
  private float widthFactor = .25f;
  private float heightFactor = .25f;
  private Vec2f coords;
  private Vec2f sides;
  private boolean selected = false;
  private Rectangle2D box;
  private int character = 0;
  private String whichPlayer = "Player 1:WASD \nprimary G, secondary H\n";
  private String charString = "teemo";
  public CharacterSelect(Vec2i startPoint, float x, int player) {
    xFactor = x;
    if(player>0) {
      whichPlayer = "Player 2:ARROW KEYS \nprimary 1, secondary 2\n";
    }
    coords = new Vec2f(startPoint.x * xFactor, startPoint.y * yFactor);
    sides = new Vec2f(startPoint.x * widthFactor, startPoint.x * heightFactor);
    box = new Rectangle2D.Float(coords.x, (int)(coords.y*1.3), sides.x, sides.y);
    Final.spriteFaces.setImageDimensions(new Vec2i((int)sides.x, (int)sides.y));    
  }
  public void incrementCharacter() {
    character = (character+1)%4;
  }
  public void decrementCharacter() {
    character = (character+3)%4;
  }
  public int character() {
    return character;
  }
  public void drawString(Graphics2D g, String text, int x, int y) {
    for (String line : text.split("\n"))
        g.drawString(line, x, y += g.getFontMetrics().getHeight());
  }
  @Override
  public void setSize(Vec2i newSize) {
    coords = new Vec2f(newSize.x * xFactor, newSize.y * yFactor);
    sides = new Vec2f(newSize.x * widthFactor, newSize.x * heightFactor);
    box = new Rectangle2D.Float(coords.x, (int)(coords.y*1.3), sides.x, sides.y);
    Final.spriteFaces.setImageDimensions(new Vec2i((int)sides.x, (int)sides.y));    
  }

  @Override
  public void drawElement(Graphics2D g) {
    setCharacter();
    Final.spriteFaces.setImageDimensions(new Vec2i((int)sides.x, (int)sides.y));
    Final.spriteFaces.drawSprite(g, new Vec2i((int)(coords.y*1.3), (int)coords.x), -1, Facing.UP, character);
    g.setColor(Color.red);
    if(selected) {
      g.setColor(Color.green);
    }
    g.draw(box);
    g.setColor(Color.black);
    int fontSize = (int)(coords.x/(xFactor*50));
    Final.leagueFont = Final.leagueFont.deriveFont(Font.PLAIN,fontSize);
    g.setFont(Final.leagueFont);
    //g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
    g.setColor(new Color(29,52,55));
    g.fillRect((int)(coords.x + sides.x * .07), (int)(coords.y - sides.y * .38), (int)(sides.x*.96), (int)((g.getFontMetrics().getHeight())*3));
    g.drawImage(Final.borderPng, (int)(coords.x + sides.x * .05), (int)(coords.y - sides.y * .4),  (int)(coords.x + sides.x * .05)+(int)sides.x, (int)(coords.y - sides.y * .4)+(int)((g.getFontMetrics().getHeight())*3.3), 0, 0, 1000, 500, null);
    g.setColor(new Color(255,215,10));
    drawString(g, whichPlayer, (int)(coords.x + sides.x * .09), (int)(coords.y - sides.y * .4));
    g.setColor(Color.black);
    drawString(g, charString, (int)(coords.x + sides.x * .05), (int)(coords.y - sides.y * .16));
    Final.leagueFont = Final.leagueFont.deriveFont(Font.PLAIN,12);
    g.setFont(Final.leagueFont);
  }
  public void setCharacter() {
    switch (character) {
    case 0 :
      charString = "teemo(ranged): \nspecial attack: shoots a blinding\ndart which prevents enemy\nattacks for a short duration";
      break;
    case 1 :
      charString = "ezreal(ranged): \nspecial attack: blinks a set\ndistance forward in the\ndirection he's facing";
      break;
    case 2 :
      charString = "yasuo(melee): \nspecial attack: summons a \ntornado which knocks up and\nstuns the enemy on impact";
      break;
    default :
      charString = "braum(melee): \nspecial attack: shoots out an \nicy ram, freezing the enemy\nfor a short duration";
      break;
    }
  }
  @Override
  public boolean isSelected(Point p) {
    return box.contains(p);
  }
  @Override
  public void setSelected(boolean s) {
    if(s && !selected) {
      whichPlayer += " LOCKED IN";
    }
    selected = s;
  }

}
