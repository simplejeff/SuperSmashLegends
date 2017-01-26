package jmhao.engine.Entity;

import java.awt.Color;
import java.awt.Graphics2D;

import jmhao.engine.Collision.Collision;
import jmhao.engine.Collision.Raycast;
import cs195n.Vec2f;
import cs195n.Vec2i;

public abstract class PhysicsEntity<T extends PhysicsEntity<T>> extends Entity {
    protected Vec2f vel = new Vec2f(0f, 0f);
    private float rest = .3f;
    protected boolean isGrenade = false;
    protected float mass = 0;
    private Vec2f impulseDir = new Vec2f(0f, 0f);
    protected int changeImpulse = 0;
    protected Vec2f pos = new Vec2f(0f, 0f);
    protected boolean isStatic = false;
    protected Vec2f force = new Vec2f(0f, 0f);
    protected Vec2f impulse = new Vec2f(0f, 0f);
    protected Vec2f accel = new Vec2f(0f, 0f);
    protected boolean touchingGround = false;
    protected Vec2f g = new Vec2f(0f, .0035f);
    public abstract void doCollide(T e);
    public Vec2f getVelocity() {
      return vel;
    }
    public boolean remove() {
      return false;
    }
    public boolean checkCrouching() {
      return false;
    }
    public void negativeGravity() {
      g = new Vec2f(0f, -.003f);
    }
    public void positiveGravity() {
      g = new Vec2f(0f, .003f);
    }
    public boolean isGrenade() {
      return isGrenade;
    }
    public void reduceGravity() {
      g = new Vec2f(0f, .0003f);
    }
    public Vec2f unit(Vec2f vector) {
      float length = (float)Math.sqrt(vector.x*vector.x + vector.y*vector.y);
      Vec2f unitVector = vector.sdiv(length);
      return unitVector;
    }
    public void updateGravity(Vec2f newG) {
      g = newG;
    }
    public Vec2f checkCollision(T e) {
      return s.collides(e.getShape());
    }
    public float getMass() {
      return mass;
    }
    public Vec2f getPos() {
      return pos;
    }
    public float getRest() {
      return rest;
    }
    public void setRest(float r) {
      rest = r;
    }
    public void setMass(float m) {
      mass = m;
    }
    public void setStatic(boolean b) {
      isStatic = b;
    }
    public boolean onCollide(Collision<T> c) {
      PhysicsEntity<T> other = c.getEntity();
      float Ua = c.getThisVel();
      float Ub = c.getOtherVel();
      float Ma = getMass();
      float Mb = other.getMass();
      float cor = (float)Math.sqrt(getRest()*other.getRest());
      float addImpulse = 0;
      /*Vec2f unitVec = unit(c.getMtv());
      Vec2f addImpulse = c.getMtv().smult(mass/45f);*/
      Vec2f displace = c.getDirMtv();
      float y = (unit(c.getMtv())).y;
      if(other.isStatic()) {
        addImpulse = (Ub - Ua) * ((Ma*(1+cor)));
      } else {
        displace = displace.smult(.5f);
        addImpulse =  (Ub - Ua) * ((Ma*Mb*(1+cor))/(Ma+Mb));
        //this is to draw the impulse being applied, it can re-draw after drawing
        //every .5 seconds. Only draws for 2 non static entities
        if(changeImpulse <= 0) {
          impulseDir = (c.getMtv()).normalized();
          impulseDir = impulseDir.smult(addImpulse);
          changeImpulse = 500;
        }
      }
      Vec2f loc = new Vec2f(s.getLocation().x, s.getLocation().y);
      Vec2f newLoc = loc.plus(displace);
      setPos(newLoc);
      Vec2f normal = ((c.getMtv()).normalized());
      normal = normal.smult(addImpulse);
      if(normal.y < -.833f) {
       // touchingGround = true;
      }
      updateImpulse(normal);
      //other.updateImpulse(-addImpulse, normal);
      return true;
    }
    public boolean isStatic() {
      return isStatic;
    }
    public void drawElement(Graphics2D g) {
      //shield.drawElement(g);
      s.drawElement(g);
      /*g.setColor(Color.red);
      Vec2i initLoc = s.getCenter();
      Vec2i finalLoc = initLoc.plus((int)impulseDir.x*100, (int)impulseDir.y*100);
      g.drawLine(initLoc.x, initLoc.y, finalLoc.x, finalLoc.y);
      g.setColor(Color.black);*/
    }
    public void updateImpulse(Vec2f i) {
      impulse = impulse.plus(i);
    }
    
    public void updateImpulse(float i, Vec2f mtv) {
      Vec2f addition = (mtv.smult(i));
      impulse = impulse.plus(mtv.smult(i));
    }
    public void nullForces() {
      impulse = new Vec2f(0, 0);
      force = new Vec2f(0, 0);
    }
    public void updateForce(Vec2f f) {
      force = force.plus(f);
    }
    public void drag(long prevTickTime) {
      float newX = vel.x*((float)Math.pow(.97f,prevTickTime));
     // float newY = vel.y*((float)Math.pow(.99f,prevTickTime));
    /*  if(Math.abs(newX) < .005) {
        newX = 0;
      }*/
   //   float newY = vel.y*((float)Math.pow(.997f,prevTickTime));
     /* if(Math.abs(newY) < .005) {
        newY = 0;
      }*/
      vel = new Vec2f(newX, vel.y);
    }
    @Override
    public void changeColor(Color c) {
      s.setColor(c);
    }
  /*  public void bounceWall() {
      vel = new Vec2f(vel.x*-1, vel.y);
      pos = pos.plus(vel);
      if(vel.x > 0) {
        pos = new Vec2f(pos.x + 5, pos.y);
      }
      if(vel.x < 0) {
        pos = new Vec2f(pos.x - 5, pos.y);
      }
      s.setLocation(new Vec2i((int)pos.x, (int)pos.y));
    }
    public void bounceGround() {
      vel = new Vec2f(vel.x, vel.y*-.97f);
      pos = pos.plus(vel);
      if(vel.y > 0) {
        pos = new Vec2f(pos.x, pos.y + 5);
      }
      if(vel.y < 0) {
        pos = new Vec2f(pos.x, pos.y - 5);
      }
      s.setLocation(new Vec2i((int)pos.x, (int)pos.y));
    }*/
    
    public void setPos(Vec2f newPos) {
      pos = newPos;
      s.setLocation(new Vec2i((int)pos.x, (int)pos.y));
    }
    
    public void addPos(Vec2f addPos) {
      pos = pos.plus(addPos);
      s.setLocation(new Vec2i((int)pos.x, (int)pos.y));
    }
    
    public void subtractPos(Vec2f subPos) {
      pos = pos.minus(subPos);
      s.setLocation(new Vec2i((int)pos.x, (int)pos.y));
    }
    @Override
    public void reset() {
      touchingGround = false;
    }
    @Override
    public void onTick(long millisSincePreviousTick) {
      changeImpulse -= millisSincePreviousTick;
      if(changeImpulse < 0) {
        changeImpulse = 0;
      }
      updateForce(g.smult(mass));
      vel = vel.plus((((force.sdiv(mass)).smult(millisSincePreviousTick)).plus((impulse.sdiv(mass)))));
      if(vel.y < -1.5) {
        vel = new Vec2f(vel.x, -1.5f);
      }
      if(vel.y > 1.5) {
        vel = new Vec2f(vel.x, 1.5f);
      }
      if(vel.x < -2) {
        vel = new Vec2f(-2, vel.y);
      }
      if(vel.x > 2) {
        vel = new Vec2f(2, vel.y);
      }
      pos = pos.plus((vel.smult(millisSincePreviousTick)));
      if(Math.abs(vel.y) < .1 && impulse.y != 0) {
      //  touchingGround = true;
      }
      if(Math.abs(vel.y) > .1) {
       // touchingGround = false;
      }
      force = new Vec2f(0, 0);
      impulse = new Vec2f(0, 0);
      //System.out.println("location : " + pos);
      s.setLocation(new Vec2i((int)pos.x, (int)pos.y));
    }
    
}
