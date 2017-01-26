package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import jmhao.engine.Entity.Entity;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.World.PhysicsWorld;
import jmhao.engine.World.World;

public class DebugWorld<T extends PhysicsEntity<T>> extends PhysicsWorld<T> {
  Vec2i currScreenSize;
  Vec2i mousePress = null;
  Vec2i selectedLoc = null;
  boolean pressed = false;
  Entity selected = null;
  @SuppressWarnings("unchecked")
  public DebugWorld(Vec2i screenSize) {
    currScreenSize = screenSize;
    T cOne = (T)new TestCircle<T>(new Vec2i(0, 0), 25);
    T cTwo = (T)new TestCircle<T>(new Vec2i(50, 50), 25);
    T rOne = (T)new TestRect<T>(new Vec2i(200, 100), 50, 50);
    T rTwo = (T)new TestRect<T>(new Vec2i(250, 150), 50, 50);
    T pOne = (T)new TestPoly<T>(new Vec2i(150, 150), new Vec2f(150, 150), new Vec2f(175, 100), new Vec2f(125, 100));
    T pTwo = (T)new TestPoly<T>(new Vec2i(100, 200), new Vec2f(100, 200), new Vec2f(80, 225), new Vec2f(100, 250), new Vec2f(130, 250), new Vec2f(150, 225), new Vec2f(130, 200));
    
    addEntity(cOne);
    addEntity(cTwo);
    addEntity(rOne);
    addEntity(rTwo);
    addEntity(pTwo);
    addEntity(pOne);
  }
  @Override
  public void getTick(long millisSincePrevTick) {
    T a;
    for(int i = 0; i < entityList.size(); i++) {
      a = entityList.get(i);
      a.reset();
    }
    super.getTick(millisSincePrevTick);
  }
  @Override
  public void onKeyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void drawElement(Graphics2D g) {
    for(Entity et : entityList) {
      et.drawElement(g);
    }
  }

  @Override
  public void onMouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onMousePressed(MouseEvent e) {
    Point p = e.getPoint();
    System.out.println("press");
    if(!pressed) {
      mousePress = new Vec2i(p.x, p.y);
      pressed = true;
    }
    for(Entity et : entityList) {
      if(et.getShape().checkPoint(new Vec2i(e.getPoint().x, e.getPoint().y))) {
        et.setSelected(true);
        selected = et;
      } else {
        et.setSelected(false);
      }
    }
  }

  @Override
  public void onMouseReleased(MouseEvent e) {
    System.out.println("release");
    for(Entity et : entityList) {
      et.setSelected(false);
    }
    selected = null;
    pressed = false;
  }

  @Override
  public void onMouseDragged(MouseEvent e) {
    Point p = e.getPoint();
    Vec2i drag = new Vec2i(p.x, p.y);
    if(selected != null) {
      Vec2i diff = mousePress.minus(drag);
      selected.changePos(diff);
      mousePress = drag;
    }
  }

  @Override
  public void onMouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onResize(Vec2i newSize) {
    // TODO Auto-generated method stub
    
  }
  @Override
  protected void onKeyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

}
