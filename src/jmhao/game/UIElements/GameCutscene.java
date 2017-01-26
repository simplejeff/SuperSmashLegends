package jmhao.game.UIElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import cs195n.Vec2i;
import jmhao.engine.Cutscene;
import jmhao.engine.Entity.Facing;
import jmhao.game.Final;

public class GameCutscene extends Cutscene {
  
  String player = "Player ";
  private int character = 0;
  public GameCutscene(Vec2i startPoint, String player, float x, float y, int character, String quote) {
    super(startPoint, x, y, quote);
    this.character = character;
    this.player += player;
    Final.spriteFaces.setImageDimensions(new Vec2i((int)boxSides.x, (int)boxSides.y));
  }
  @Override
  public void setSize(Vec2i newSize) {
    super.setSize(newSize);
    Final.spriteFaces.setImageDimensions(new Vec2i((int)boxSides.x, (int)boxSides.y));
  }

  @Override
  public void drawElement(Graphics2D g) {
    Final.spriteFaces.setImageDimensions(new Vec2i((int)boxSides.x, (int)boxSides.y));
    Final.spriteFaces.drawSprite(g, new Vec2i((int)coords.y, (int)coords.x), -1, Facing.UP, character);
    int fontSize = (int)(boxSides.x/(xFactor*90));
    Final.leagueFont = Final.leagueFont.deriveFont(Font.PLAIN,fontSize);
    g.setFont(Final.leagueFont);
   
    super.drawElement(g);
   // g.draw(box);
   // g.draw(textBox);
    g.drawImage(Final.borderPng, (int)(textBox.getMinX()*.98), (int)(textBox.getMinY()*.96), (int)(textBox.getMaxX()*1.01), (int)(textBox.getMaxY()*1.02), 0, 0, 1000, 500, null);
    
    fontSize = (int)(boxSides.x/(xFactor*50));
    Final.leagueFont = Final.leagueFont.deriveFont(Font.PLAIN,fontSize);
    g.setFont(Final.leagueFont);
    g.setColor(Color.green);
    // g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
    g.drawString(player, (int)(box.getMinX() + 5), (int)(coords.y + sides.y * .2));
    g.setColor(Color.black);
    Final.leagueFont = Final.leagueFont.deriveFont(Font.PLAIN,16);
    g.setFont(Final.leagueFont);
  }
}
