package jmhao.engine.Drawing;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import jmhao.engine.Entity.Facing;

public abstract class Sprite {
  protected Animation[] myAnims;
  protected Vec2f dim;
  protected Image sprites;
  protected Vec2i imageDim = new Vec2i(1, 1);
  int time;
  
  public Sprite(int numAnimations, Image mySprites) {
    myAnims = new Animation[numAnimations];
    sprites = mySprites;
  }
  
  public void addAnimation(Animation a, int index) {
    if(myAnims.length > index) {
      myAnims[index] = a;
    }
  }
  public void drawSprite(Graphics2D g, Vec2i loc, int moving, Facing facing, int index) {
    Vec2f spriteLoc;
    AffineTransform original = g.getTransform();
    spriteLoc = myAnims[index].getPos();
  /*  if(moving == -1) {
      spriteLoc = myAnims[index].getPos();
    } else {
      if(facing == Facing.UP) {
        g.translate(0, -(double)moving/6);
      } else if(facing == Facing.RIGHT) {
        g.translate((double)moving/6, 0);
      } else if(facing == Facing.DOWN) {
        g.translate(0, (double)moving/6);
      } else {
        g.translate(-(double)moving/6, 0);
      }
      spriteLoc = myAnims[index].getPos().plus(myAnims[index].getDir().smult(moving));
    }*/
    g.drawImage(sprites, loc.y, loc.x, loc.y+imageDim.y, loc.x+imageDim.x, (int)(spriteLoc.x*dim.x), (int)(spriteLoc.y*dim.y), (int)(spriteLoc.x*dim.x + dim.x), (int)(spriteLoc.y*dim.y + dim.y), null);
    g.setTransform(original);
  }
  public void setDimensions(Vec2f newDim) {
    dim = new Vec2f(newDim.x, newDim.y);
  }
  public void setImageDimensions(Vec2i newDim) {
    imageDim = newDim;
  }
}
