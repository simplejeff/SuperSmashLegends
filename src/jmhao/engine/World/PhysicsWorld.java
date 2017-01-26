package jmhao.engine.World;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.util.ArrayList;

import jmhao.engine.Collision.Collision;
import jmhao.engine.Collision.Raycast;
import jmhao.engine.Entity.Entity;
import jmhao.engine.Entity.PhysicsEntity;

public abstract class PhysicsWorld<T extends PhysicsEntity<T>> extends World {
  protected ArrayList<T> entityList = new ArrayList<T>();
  protected ArrayList<T> nonCollide = new ArrayList<T>();
  public void addEntity(T e) {
    entityList.add(e);
  }
  public void addNonCollide(T e) {
    nonCollide.add(e);
  }
  public Vec2f unit(Vec2f vector) {
    float length = dist(vector);
    if(length == 0) {
      return new Vec2f(0, 0);
    } else {
      Vec2f unitVector = vector.sdiv(length);
      return unitVector;
    }
  }
  public float dot(Vec2f a, Vec2f b) {
    return a.x*b.x + a.y*b.y;
  }
  public float project(Vec2f b, Vec2f a) {
   Vec2f unitA = a.normalized();
   float proj = dot(unitA, b);
   return proj;
  }
  public float dist(Vec2f vector) {
    float length = (float)Math.sqrt(vector.x*vector.x + vector.y*vector.y);
    return length;
  }
  /**
   * getTick gets the tick since previous tick
   * @param millisSincePrevTick milliseconds since prev tick
   */
  public void getTick(long millisSincePrevTick) {
    T a, b;
    Vec2f myMtv, otherMtv;
    float myVel, otherVel;
    Collision<T> otherC, thisC;
    for(T e : nonCollide) {
      e.onTick(millisSincePrevTick);
    }
    for(int i = 0; i < entityList.size(); i++) {
      a = entityList.get(i);
      //a.reset();
      //a.changeColor(Color.black);
      for(int j = i + 1; j < entityList.size(); j++) {
          b = entityList.get(j);
          myMtv = a.checkCollision(b);
          if(myMtv != null && !myMtv.isZero()) {
            otherMtv = myMtv.smult(-1);
            myVel = project(a.getVelocity(), myMtv);
            otherVel = project(b.getVelocity(), myMtv);
           /* myVel = (unit(otherMtv)).smult(dist(a.getVelocity()));
            otherVel = (unit(myMtv)).smult(dist(b.getVelocity()));*/
            thisC = new Collision<T>(b, myMtv, myMtv, myVel, otherVel, a.getShape(), b.getShape());
            otherC = new Collision<T>(a, myMtv, otherMtv, otherVel, myVel, b.getShape(), a.getShape());
            a.onCollide(thisC);
            b.onCollide(otherC);
            a.doCollide(b);
          }
      }
      a.onTick(millisSincePrevTick);
    }
  }
  public Raycast<T> checkRaycast(Vec2i startPos, Vec2f vec, T origin, float radius) {
    T a;
    float bestDist = radius;
    Vec2i bestIntersect = null;
    T best = null;
    Vec2f normalVec = (vec.normalized());
    for(int i = 0; i < entityList.size(); i++) {
      a = entityList.get(i);
      if(a != origin) {
        Vec2i intersect = a.getShape().checkRay(startPos, normalVec);
        if(intersect != null) {
          Vec2i diff = intersect.minus(startPos);
          float dist = dist(new Vec2f(diff.x, diff.y));
          if(Math.abs(dist) < Math.abs(bestDist)) {
            bestDist = dist;
            bestIntersect = intersect;
            best = a;
          }
        }
      }
    }
    if(best != null) {
      return new Raycast<T>(best, startPos, bestIntersect, vec);
    }
    return null;
  }
}
