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
import jmhao.engine.Shape.Comp;
import jmhao.engine.Shape.Polygon;
import jmhao.engine.Shape.Rectangle;

public class Enemy2<T extends PhysicsEntity<T>> extends PhysicsEntity<T>{
  GameWorld<T> myGW;
  boolean movingLeft = false;
  boolean movingRight = false;
  boolean touchingGround = false;
  int myHeight;
  int yVal;
  Vec2f vel;
  int facing = 1;
  boolean touchingPlayer = false;
  final float gravity = .001f;
  double reload = 0;
  public Enemy2(GameWorld<T> gw, Vec2i startPoint, int size, float velX) {
    myGW = gw;
    myHeight = size*2;
    Circle c = new Circle(startPoint, size);
    Polygon p = new Polygon(startPoint, new Vec2f((float)(startPoint.x),(float)(startPoint.y)), new Vec2f((float)(startPoint.x+size/2),(float)(startPoint.y+size*2)), new Vec2f((float)(startPoint.x+(3*size/2)),(float)(startPoint.y+size*2)), new Vec2f((float)(startPoint.x+size*2),(float)(startPoint.y)));
    Rectangle r = new Rectangle(new Vec2i(startPoint.x, startPoint.y-size), size*2, size*2);
    Vec2i firstOffset = new Vec2i(0,0);
    Vec2i secondOffset = new Vec2i(0, -size);
    Comp comp = new Comp(startPoint, c, p);
    comp.addOffsets(firstOffset, secondOffset);
    setShape(comp);
    setPos(new Vec2f(startPoint.x, startPoint.y));
    if(velX > 0) {
      movingRight = true;
      movingLeft = false;
    } else {
      movingLeft = true;
      movingRight = false;
    }
    updateForce(new Vec2f(velX, 0));
  }
  @Override
  public void doCollide(T e) {
    ((GamePhysicsEntity<T>)e).collideEnemy2(this);
  }
  /*@Override
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
    reload -= millisSincePreviousTick;
    if(reload < 10) {
      reload = 0;
      changeColor(Color.black);
    }
    super.onTick(millisSincePreviousTick);
    /*if(!movingLeft && !movingRight) {
      drag(millisSincePreviousTick);
    }*/
    vel = getVelocity();
    if(vel.x > 0) {
      facing = 1;
    }
    if(vel.x < 0) {
      facing = -1;
    }
    //s.setLocation(new Vec2i((int)pos.x, (int)pos.y));
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
    return EntityType.ENEMY2;
  }
  @Override
  public void reset() {
    super.reset();
  }

}
