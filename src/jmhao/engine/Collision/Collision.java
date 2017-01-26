package jmhao.engine.Collision;

import cs195n.Vec2f;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Shape.Shape;

public class Collision<T extends PhysicsEntity<T>> {
  final T other;
  final Vec2f mtv;
  final Vec2f dirMtv;
  final float thisVel;
  final float otherVel;
  final Shape thisShape;
  final Shape otherShape;
  public Collision(T p, Vec2f myMtv, Vec2f myDirMtv, float myVel, float thatVel, Shape thisS, Shape thatS) {
    other = p;
    mtv = myMtv;
    dirMtv = myDirMtv;
    thisVel = myVel;
    otherVel = thatVel;
    thisShape = thisS;
    otherShape = thatS;
  }
  public T getEntity() {
    return other;
  }
  public Vec2f getMtv() {
    return mtv;
  }
  public Vec2f getDirMtv() {
    return dirMtv;
  }
  public float getThisVel() {
    return thisVel;
  }
  public float getOtherVel() {
    return otherVel;
  }
  public Shape getThisShape() {
    return thisShape;
  }
  public Shape getOtherShape() {
    return otherShape;
  }
}
