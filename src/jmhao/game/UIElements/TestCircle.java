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
import jmhao.engine.Shape.Shape;
import jmhao.engine.Shape.Circle;

public class TestCircle<T extends PhysicsEntity<T>> extends PhysicsEntity<T> {
  Circle c;
  boolean selected = false;
  Vec2f collisionVec;
  boolean colliding = false;
  public TestCircle(Vec2i startLoc, double radius) {
    c = new Circle(startLoc, radius);
    setShape(c);
  }
  @Override
  public boolean onCollide(Collision<T> c) {
    s.setColor(Color.red);
    colliding = true;
    collisionVec = c.getMtv();
    return true;
  }
  @Override
  public void updateImpulse(Vec2f i) {
    
  }
  @Override
  public void updateForce(Vec2f f) {
    
  }
  @Override
  public void onTick(long millisSincePrevTick) {
    
  }
  @Override
  public void changeColor(Color newC) {
    s.setColor(newC);
  }
  @Override
  public void drawElement(Graphics2D g) {
    s.drawElement(g);
    if(colliding) {
      Vec2i initLoc = s.getCenter();
      Vec2i finalLoc = initLoc.plus((int)collisionVec.x, (int)collisionVec.y);
      g.drawLine(initLoc.x, initLoc.y, finalLoc.x, finalLoc.y);
    }
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
    return s.isSelected(p);
  }

  @Override
  public Shape getShape() {
    return s;
  }
  @Override
  public void setSelected(boolean s) {
    selected = s;
  }
  @Override
  public EntityType getEntity() {
    // TODO Auto-generated method stub
    return null;
  }
  @Override
  public void changePos(Vec2i change) {
    Vec2i center = ((Circle)s).getCenter();
    double r = ((Circle)s).getRad();
    Vec2i corner = new Vec2i((int)(center.x - r), (int)(center.y - r));
    corner = corner.minus(change);
    s.setLocation(corner);
  }
  @Override
  public void onKeyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void reset() {
    colliding = false;
    changeColor(Color.black);
  }
  @Override
  public void doCollide(T e) {
    // TODO Auto-generated method stub
    
  }
  
}
