package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import jmhao.engine.Collision.Collision;
import jmhao.engine.Entity.EntityType;
import jmhao.engine.Entity.Input;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Shape.Rectangle;

public class WorldEnder<T extends PhysicsEntity<T>> extends GamePhysicsEntity<T>{
  Input doEndWorld;
  Input doWinWorld;
  GameWorld<T> myGW;
  public WorldEnder(GameWorld<T> gw) {
    myGW = gw;
    Rectangle r = new Rectangle(new Vec2i(0, 0), 0, 0);
    setShape(r);
    doEndWorld = new Input() { public void run(HashMap<String, String> args){myGW.loseGame();}};
    doWinWorld = new Input() { public void run(HashMap<String, String> args){myGW.winGame();}};
    addInput(doEndWorld, "doEndWorld");
    addInput(doWinWorld, "doWinWorld");
    setStatic(true);
  }
  @Override
  public boolean onCollide(Collision<T> c) {
    return true;
  }
  @Override
  public void doCollide(T e) {
    //((GamePhysicsEntity<T>)e).collideBox(this);
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
    s.setColor(newC);
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
    return EntityType.BACKGROUNDHORIZ;
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
