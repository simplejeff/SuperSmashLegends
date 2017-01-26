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

public class Shield<T extends PhysicsEntity<T>> extends PhysicsEntity<T>{
  GameWorld myGW;
  int myHeight;
  int yVal;
  Vec2f vel;
  double reload = 0;
  boolean shielding = false;
  public Shield(GameWorld gw, Vec2i startPoint, int size) {
    myGW = gw;
    myHeight = size*2;
    Circle c = new Circle(startPoint, size);
    setShape(c);
    changeColor(Color.BLUE);
    changePos(startPoint);
  }
 /* @Override
  public boolean checkCollision(Entity e) {
    boolean b = s.collides(e.getShape());
    if(b) {
      switch(e.getEntity()) {
      case ENEMY2:
        if(shielding) {
          myGW.removeEntity(e);
          reload = 0;
          shielding = false;
        }
        break;
      }
    }
    return b;
  }*/
  @Override
  public void onTick(long millisSincePreviousTick) {
    reload -= millisSincePreviousTick;
    if(reload < 10) {
      reload = 0;
      shielding = false;
    }
    myGW.updateShield((int)(reload/10));
  }

  @Override
  public void changePos(Vec2i change) {
    s.setLocation(change);
  }

  @Override
  public void changeColor(Color newC) {
    s.setColor(newC);
  }
  public double getShield() {
    return reload;
  }
  public boolean isShielding() {
    return shielding;
  }
  @Override
  public void drawElement(Graphics2D g) {
    if(shielding) {
      s.drawElement(g);
    }
  }

  @Override
  public void onKeyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    switch(keyCode) {
    case KeyEvent.VK_CONTROL: 
      if(!shielding){
        shielding = true;
        reload = 1000;
      }
      break;
    default:
      break;
    }
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
    return EntityType.SHIELD;
  }
  @Override
  public void onKeyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    switch(keyCode) {
    case KeyEvent.VK_CONTROL: 
      if(shielding){
        shielding = false;
        reload = 0;
      }
      break;
    default:
      break;
    }
  }
  @Override
  public void reset() {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void doCollide(T e) {
    // TODO Auto-generated method stub
    
  }
  
}