package jmhao.engine.Entity;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import jmhao.engine.Shape.Rectangle;

public class Door<T extends PhysicsEntity<T>> extends PhysicsEntity<T>{
  private Input doOpen, doClose;
  double w, l;
  Vec2i startPos;
  public Door(Vec2f startLoc, float width, float length) {
    startPos = new Vec2i((int)startLoc.x, (int)startLoc.y);
    Rectangle r = new Rectangle(new Vec2i((int)startLoc.x, (int)startLoc.y), width, length);
    setShape(r);
    setStatic(true);
    setPos(startLoc);
    doOpen = new Input() { public void run(HashMap<String, String> args){setOpen();}};
    doClose = new Input() { public void run(HashMap<String, String> args){setClose();}};
    addInput(doOpen, "doOpen");
    addInput(doClose, "doClose");
  }
  public void setOpen() {
    Rectangle r = new Rectangle(startPos, 0, 0);
    setShape(r);
  }
  public void setClose() {
    Rectangle r = new Rectangle(startPos, w, l);
    setShape(r);
  }
  @Override
  public void onTick(long millisSincePreviousTick) {
    // TODO Auto-generated method stub
  }
  @Override
  public void doCollide(T e) {    
  }

  @Override
  public void changePos(Vec2i change) {
    // TODO Auto-generated method stub
    
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

