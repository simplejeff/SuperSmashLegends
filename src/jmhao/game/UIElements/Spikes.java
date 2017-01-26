package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import jmhao.engine.Entity.Entity;
import jmhao.engine.Entity.EntityType;
import jmhao.engine.Entity.Input;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Shape.Circle;
import jmhao.engine.Shape.Polygon;
import jmhao.engine.Shape.Rectangle;

public class Spikes<T extends PhysicsEntity<T>> extends GamePhysicsEntity<T>{
  GameWorld<T> myGW;
  Input doFall;
  boolean gravity = false;
  public Spikes(GameWorld<T> gw, Vec2f startLoc, float width, float length) {
    myGW = gw;
    Rectangle r = new Rectangle(new Vec2i((int)startLoc.x, (int)startLoc.y), width, length);
    setShape(r);
    setPos(startLoc);
    doFall = new Input() { public void run(HashMap<String, String> args){gravity = true;}};
    addInput(doFall, "doFall");
    //updateForce(new Vec2f(velX, 0));
  }
  @Override
  public void doCollide(T e) {
    System.out.println("collide");
    ((GamePhysicsEntity<T>)e).collideSpikes(this);
  }
  @Override
  public void collidePlayer(Player<T> e) {
    myGW.loseGame();
  }
 /* @Override
  public boolean checkCollision(Entity e) {
    boolean b = s.collides(e.getShape());
    if(b) {
      switch(e.getEntity()) {
      case PLAYER:
        if(reload < 10) {
          myGW.lowerHp();
          changeColor(Color.red);
          reload = 500;
        }
        break;
      case BACKGROUNDVERT:
        touchingGround = true;
        Vec2i ground = e.getShape().getLocation();
        yVal = ground.y - myHeight;
        Vec2i newPos = s.getLocation();
        setPos(new Vec2f(newPos.x, yVal));
        bounceGround();
        break;
      case BACKGROUNDHORIZ:
        bounceWall();
        movingLeft = !movingLeft;
        movingRight = !movingRight;
        if(movingLeft) {
          updateVelocityX(-.3f);
        } else {
          updateVelocityX(.3f);
        }
        break;
      case BULLET:
        myGW.removeEntity(this);
        myGW.removeEntity(e);
      }
    }
    return b;
  }*/
  @Override
  public void changePos(Vec2i change) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void onTick(long millisSincePreviousTick) {
    if(gravity) {
      //reduceGravity();
      super.onTick(millisSincePreviousTick);
    }
  }
  @Override
  public void drawElement(Graphics2D g) {
    super.drawElement(g);
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
    /*Vec2i playerLoc = getShape().getLocation();
    int leftLimit = playerLoc.x;
    double percentWidth = (double)leftLimit/screenSize.x;
    screenSize = newSize;
    int xChange = (int)(percentWidth*screenSize.x);
    Vec2f newPlayerLoc = new Vec2f(xChange, playerLoc.y);
    setPos(newPlayerLoc);*/
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
    return EntityType.ENEMY;
  }
  @Override
  public void reset() {
    super.reset();
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
