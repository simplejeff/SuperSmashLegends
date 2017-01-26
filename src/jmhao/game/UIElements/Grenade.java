package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import jmhao.engine.Collision.Collision;
import jmhao.engine.Collision.Raycast;
import jmhao.engine.Entity.Entity;
import jmhao.engine.Entity.EntityType;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Shape.Circle;

public class Grenade<T extends PhysicsEntity<T>> extends GamePhysicsEntity<T>{
  Vec2f velocity;
  int counter = 300;
  GameWorld<T> myGW;
  ArrayList<Vec2f> unitCircle = new ArrayList<Vec2f>();
  public Grenade(GameWorld<T> gw, Vec2i startPoint, Vec2f thisVelocity) {
    isGrenade = true;
    myGW = gw;
    unitCircle.add(new Vec2f(1, 0));
    unitCircle.add(new Vec2f((float)(Math.sqrt(2)/2), (float)(Math.sqrt(2)/2)));
    unitCircle.add(new Vec2f(0, 1));
    unitCircle.add(new Vec2f(-(float)(Math.sqrt(2)/2), (float)(Math.sqrt(2)/2)));
    unitCircle.add(new Vec2f(-1, 0));
    unitCircle.add(new Vec2f(-(float)(Math.sqrt(2)/2), -(float)(Math.sqrt(2)/2)));
    unitCircle.add(new Vec2f(0, -1));
    unitCircle.add(new Vec2f((float)(Math.sqrt(2)/2), -(float)(Math.sqrt(2)/2)));
    Circle c = new Circle(startPoint, 7);
    setShape(c);
    setPos(new Vec2f(startPoint.x, startPoint.y));
    updateForce(thisVelocity);
  }
  @Override
  public void doCollide(T e) {
    myGW.removeGrenade(this);
    myGW.removeEntity(this);
  }
  @Override
  public boolean onCollide(Collision<T> c) {
    Vec2i center = getShape().getCenter();
    for(Vec2f f : unitCircle) {
      Raycast<T> r = myGW.checkRaycast(center, f, (T)this, 400);
      if(r != null) {
        PhysicsEntity<T> e = r.getEntity();
        e.updateImpulse(r.getVec());
        Ray<T> ray = new Ray<T>(myGW, r.getStart(), r.getIntersect());
        myGW.addRay(ray);
      }
    }
    myGW.removeGrenade(this);
    myGW.removeEntity(this);
    return true;
  }
  @Override
  public void onTick(long millisSincePreviousTick) {
    if(counter >= 0) {
      counter -= millisSincePreviousTick;
    }
    if(counter <= 0) {
      myGW.removeGrenade(this);
    }
    super.onTick(millisSincePreviousTick);
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
    s.drawElement(g);
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
