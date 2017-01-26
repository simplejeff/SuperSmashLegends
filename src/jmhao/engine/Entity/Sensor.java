package jmhao.engine.Entity;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import jmhao.engine.Shape.Rectangle;

public class Sensor<T extends PhysicsEntity<T>> extends PhysicsEntity<T>{
  private Output onSensorTrigger;
  boolean visibility;
  public Sensor(Vec2f startLoc, float width, float length, boolean visible) {
    Rectangle r = new Rectangle(new Vec2i((int)startLoc.x, (int)startLoc.y), width, length);
    setShape(r);
    setStatic(true);
    setPos(startLoc);
    visibility = visible;
    onSensorTrigger = new Output();
    addOutput(onSensorTrigger, "onSensorTrigger"); 
  }
  
  @Override
  public void doCollide(T e) {
    //s.setColor(Color.green);
    onSensorTrigger.run();
  }

  @Override
  public void changePos(Vec2i change) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void onTick(long millisSincePreviousTick) {
    // TODO Auto-generated method stub
  }
  @Override
  public void drawElement(Graphics2D g) {
    if(visibility) {
      s.drawElement(g);
    }
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
    // TODO Auto-generated method stub
    return null;
  }

}
