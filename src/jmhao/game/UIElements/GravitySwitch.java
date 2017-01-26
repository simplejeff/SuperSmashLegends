package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import jmhao.engine.Entity.EntityType;
import jmhao.engine.Entity.Input;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Shape.Rectangle;

public class GravitySwitch<T extends PhysicsEntity<T>> extends GamePhysicsEntity<T>{
  private Input doUp, doDown;
  GameWorld<T> myGW;
  public GravitySwitch(GameWorld<T> gw) {
    myGW = gw;
    Rectangle r = new Rectangle(new Vec2i(0, 0), 0, 0);
    setShape(r);
    setStatic(true);
    doUp= new Input() { public void run(HashMap<String, String> args){myGW.gravityUp();}};
    doDown = new Input() { public void run(HashMap<String, String> args){myGW.gravityDown();}};
    addInput(doUp, "doUp");
    addInput(doDown, "doDown");
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

