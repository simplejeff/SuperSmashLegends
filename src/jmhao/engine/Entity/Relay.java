package jmhao.engine.Entity;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import jmhao.engine.Shape.Rectangle;

public class Relay<T extends PhysicsEntity<T>> extends PhysicsEntity<T>{
  private Output onRelayTrigger;
  private Input doEnable, doDisable;
  boolean open = false;
  public Relay(Vec2f startLoc, float width, float length) {
    Rectangle r = new Rectangle(new Vec2i((int)startLoc.x, (int)startLoc.y), width, length);
    setShape(r);
    s.setColor(Color.red);
    setStatic(true);
    setPos(startLoc);
    onRelayTrigger = new Output();
    doEnable = new Input() { public void run(HashMap<String, String> args){s.setColor(Color.green); open = true;}};
    doDisable = new Input() { public void run(HashMap<String, String> args){open = false;}};
    addOutput(onRelayTrigger, "onRelayTrigger"); 
    addInput(doEnable, "doEnable");
    addInput(doDisable, "doDisable");
  }
  @Override
  public void doCollide(T e) {    
    if(open) {
      onRelayTrigger.run();
    }
  }
  @Override
  public void onTick(long millisSincePreviousTick) {
    // TODO Auto-generated method stub
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

