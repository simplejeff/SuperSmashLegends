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
import jmhao.engine.Shape.Rectangle;
import jmhao.game.Final;

public class BackgroundVert<T extends PhysicsEntity<T>> extends GamePhysicsEntity<T>{
  double myLength;
  int width, length;
  public BackgroundVert(Vec2f startLoc, float width, float length) {
    Rectangle r = new Rectangle(new Vec2i((int)startLoc.x, (int)startLoc.y), width, length);
    this.width = (int)width;
    this.length = (int)length;
    myLength = length;
    setShape(r);
    setStatic(true);
  }
  @Override
  public boolean onCollide(Collision<T> c) {
    return true;
  }
  @Override
  public void doCollide(T e) {
    ((GamePhysicsEntity<T>)e).collideVertWall(this);
  }
  @Override
  public void updateImpulse(Vec2f i) {
    
  }
  @Override
  public void updateForce(Vec2f f) {
    
  }
  @Override
  public void onTick(long millisSincePreviousTick) {
    // TODO Auto-generated method stub
    vel = new Vec2f(0f, 0f);
  }
  public double getLength() {
    return myLength;
  }

  @Override
  public void changePos(Vec2i change) {
    // TODO Auto-generated method stub
    
  }
  public void changeRect(Vec2i change, double width, double length) {
    Rectangle r = new Rectangle(change, width, length);
    setShape(r);
    s.setLocation(change);
  }

  @Override
  public void changeColor(Color newC) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void drawElement(Graphics2D g) {
   //.drawElement(g);
    g.drawImage(Final.platformPng, s.getLocation().x, s.getLocation().y-20, s.getLocation().x + width + 40, s.getLocation().y + (int)(length*2.3), 620, 840, 2380, 1500, null);
   // s.drawElement(g);
  }

  @Override
  public void onKeyPressed(KeyEvent e) {
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
    return EntityType.BACKGROUNDVERT;
  }
  @Override
  public void onKeyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
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
