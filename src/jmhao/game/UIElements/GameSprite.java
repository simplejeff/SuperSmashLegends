package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import jmhao.engine.Drawing.Sprite;

public class GameSprite extends Sprite {
  public GameSprite(int numAnimations, Image mySprites) {
    super(numAnimations, mySprites);
  }
  public void drawSprite(Graphics2D g, Vec2i loc, int facing, int index) {
    Vec2f spriteLoc;
    AffineTransform original = g.getTransform();
    spriteLoc = myAnims[index].getPos();
    if(facing == 1) {
      g.drawImage(sprites, loc.x, loc.y, loc.x+imageDim.x, loc.y+imageDim.y, (int)(spriteLoc.x*dim.x), (int)(spriteLoc.y*dim.y), (int)(spriteLoc.x*dim.x + dim.x), (int)(spriteLoc.y*dim.y + dim.y), null);
    }//flip horizontally
    else {
      g.drawImage(sprites, loc.x + imageDim.x, loc.y, loc.x, loc.y+imageDim.y, (int)(spriteLoc.x*dim.x), (int)(spriteLoc.y*dim.y), (int)(spriteLoc.x*dim.x + dim.x), (int)(spriteLoc.y*dim.y + dim.y), null);
    }
    g.setTransform(original);
  }
}
