package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

import jmhao.engine.Entity.Entity;
import jmhao.engine.Entity.EntityType;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Shape.Circle;
import jmhao.engine.Shape.Rectangle;
import jmhao.game.Final;

public class Blink<T extends PhysicsEntity<T>> extends GamePhysicsEntity<T>{
  Vec2f velocity;
  boolean playerOne;
  boolean remove = false;
  long totalMillis = 0;
  int moving = 0;
  float totalTime = 500;
  public Blink(Vec2i startPoint) {
    this.playerOne = playerOne;
    Rectangle newR = new Rectangle(new Vec2i(startPoint.x, startPoint.y), 20, 40);
    setShape(newR);
    setPos(new Vec2f(startPoint.x, startPoint.y));
    setMass(1f);
    setRest(.5f);
    updateGravity(new Vec2f(0, 0));
  }
  @Override
  public boolean remove() {
    return remove;
  }
  @Override
  public void onTick(long millisSincePreviousTick) {
    totalTime -= millisSincePreviousTick;
    if(totalTime <= 0) {
      remove = true;
    }
    totalMillis += millisSincePreviousTick;
    moving = ((int)(totalMillis/125))%4;
    super.onTick(millisSincePreviousTick);
  }
  /*@Override
  public boolean checkCollision(Entity e) {
    boolean b = s.collides(e.getShape());
    if(b) {
      switch(e.getEntity()) {
      case ENEMY:
        break;
      case BACKGROUNDHORIZ:
        myGW.removeEntity(this);
      }
    }
    return b;
  }*/
  @Override
  public void changePos(Vec2i change) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void changeColor(Color newC) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void drawElement(Graphics2D g) {
   // s.drawElement(g);
    Final.spriteBlink.drawSprite(g, s.getLocation(), 0, moving);
  }

  @Override
  public void onKeyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onKeyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setSize(Vec2i newSize) {
    // TODO Auto-generated method stub
    
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

  @Override
  public EntityType getEntity() {
    return EntityType.BULLET;
  }
  @Override
  public void reset() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void doCollide(T e) {
  }
  @Override
  public void collidePlayer(Player<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collideVertWall(BackgroundVert<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collideHorizWall(BackgroundHoriz<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collideBoss(Boss<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collideGrenade(Grenade<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collideEnemy(Enemy<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collideEnemy2(Enemy2<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collidePlayer2(Player2<T> e) {
    // TODO Auto-generated method stub
    
  }

}
