package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;

import jmhao.engine.Collision.Collision;
import jmhao.engine.Entity.Entity;
import jmhao.engine.Entity.EntityType;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Shape.Circle;

public class Ray<T extends PhysicsEntity<T>> extends GamePhysicsEntity<T>{
  Vec2i start, stop;
  int counter = 300;
  GameWorld<T> myGW;
  public Ray(GameWorld<T> gw, Vec2i startPoint, Vec2i endPoint) {
    myGW = gw;
    Circle c = new Circle(startPoint, 7);
    setShape(c);
   // velocity = thisVelocity;
    setPos(new Vec2f(startPoint.x, startPoint.y));
    start = startPoint;
    stop = endPoint;
  }
  @Override
  public void doCollide(T e) {
    
  }
  /*@Override
  public boolean onCollide(Collision<T> c) {
    Vec2i center = getShape().getCenter();
    
    return true;
  }*/
  @Override
  public void onTick(long millisSincePreviousTick) {
    if(counter >= 0) {
      counter -= millisSincePreviousTick;
    }
    if(counter <= 0) {
      myGW.removeRay(this);
    }
  }
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
    g.setColor(Color.red);
    g.drawLine(start.x, start.y, stop.x, stop.y);
    g.setColor(Color.black);
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
