package jmhao.engine.Collision;

import cs195n.Vec2f;
import cs195n.Vec2i;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Shape.Shape;

public class Raycast<T extends PhysicsEntity<T>> {
  final T other;
  final Vec2i start;
  final Vec2i intersect;
  final Vec2f vec;
  public Raycast(T p, Vec2i myStart, Vec2i myIntersect, Vec2f myVec) {
    other = p;
    start = myStart;
    intersect = myIntersect;
    vec = myVec;
  }
  public T getEntity() {
    return other;
  }
  public Vec2i getStart() {
    return start;
  }
  public Vec2i getIntersect() {
    return intersect;
  }
  public Vec2f getVec() {
    return vec;
  }
}