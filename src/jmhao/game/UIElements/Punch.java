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

public class Punch<T extends PhysicsEntity<T>> extends GamePhysicsEntity<T>{
  Vec2f velocity;
  GameWorld<T> myGW;
  boolean playerOne;
  boolean remove = false;
  int facing;
  long totalMillis = 0;
  int moving = 0;
  float totalTime = 300;
  public Punch(GameWorld<T> gw, Vec2i startPoint, Vec2f thisVelocity, boolean playerOne, int facing) {
    myGW = gw;
    mass = 10;
    this.playerOne = playerOne;
    velocity = thisVelocity.smult(.4f);
    this.facing = facing;
    startPoint = startPoint.minus(new Vec2i(50, 10));
    if(this.facing < 0) {
      startPoint = startPoint.minus(new Vec2i(0, 0));
    }
    int move;
    if(velocity.x > 0) {
      this.facing = 1;
      move = 40;
    } else {
      this.facing = 0;
      move = -40;
    }
    startPoint = startPoint.minus(new Vec2i(move, 10));
    Rectangle r = new Rectangle(startPoint, 100, 20);
    setShape(r);
    setPos(new Vec2f(startPoint.x, startPoint.y));
    setMass(20f);
    setRest(.8f);
    updateGravity(new Vec2f(0, 0));
    updateForce(new Vec2f(velocity.x*2, 0));
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
    //s.drawElement(g);
   Final.spriteMeleePunch.drawSprite(g, s.getLocation().minus(new Vec2i(0, 10)), facing, moving);
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
    ((GamePhysicsEntity<T>)e).collidePunch(this);
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
