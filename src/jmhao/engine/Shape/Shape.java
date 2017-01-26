package jmhao.engine.Shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import cs195n.Vec2f;
import cs195n.Vec2i;

public abstract class Shape {
  public abstract void setLocation(Vec2i newLoc);
  public abstract void setColor(Color newC);
  public abstract void drawElement(Graphics2D g);
  public abstract boolean isSelected(Point p);
  public abstract Vec2f collides(Shape o);
  public abstract Vec2f collidesCircle(Circle c);
  public abstract Vec2f collidesRect(Rectangle r);
  public abstract Vec2f collidesComp(Comp m);
  public abstract Vec2f collidesPolygon(Polygon p);
  public abstract boolean checkPoint(Vec2i p);
  public abstract Vec2i getLocation();
  public abstract Vec2i getCenter();
  public abstract Vec2i checkRay(Vec2i startPos, Vec2f vec);
  public boolean checkMTV(Vec2f mtv, Vec2i aCenter, Vec2i bCenter) {
    Vec2i centerDiff = aCenter.minus(bCenter);
    int dot = (int)(mtv.x*centerDiff.x + mtv.y*centerDiff.y);
    return dot >= 0;
  }
  public float cross(Vec2f u, Vec2f v) {
    return u.x*v.y - u.y*v.x;
  }
  public float dist(Vec2f vector) {
    float length = (float)Math.sqrt(vector.x*vector.x + vector.y*vector.y);
    return length;
  }
  public float distBetween(Vec2i v1, Vec2i v2) {
    int x = v2.x - v1.x;
    int y = v2.y - v1.y;
    float length = (float)Math.sqrt(((float)(x*x + y*y)));
    return length;
  }
  public Vec2i intersectEdge(Vec2i startPos, Vec2f d, Vec2f a, Vec2f b, Vec2f n) {
    Vec2f p = new Vec2f(startPos.x, startPos.y);
    float crossA = (cross(a.minus(p), d));
    float crossB = (cross(b.minus(p), d));
    if(crossA*crossB <= 0) {
      float t = (dot(b.minus(p), n))/(dot(d, n));
      if(t >= 0) {
        Vec2f diff = d.smult(t);
        Vec2i diffInt = new Vec2i((int)diff.x, (int)diff.y);
        Vec2i intersect = startPos.plus(diffInt);
        return intersect;
      }
    }
    return null;
  }
  public Float intervalMTV(Vec2f a, Vec2f b) {
    float aRight = b.y - a.x;
    float aLeft = a.y - b.x;
    if(aLeft < 0 || aRight < 0) {
      return null;
    }
    if(aRight < aLeft) {
      return aRight;
    } else {
      return -aLeft;
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
  public Vec2f unit(Vec2f vector) {
    float length = (float)Math.sqrt(vector.x*vector.x + vector.y*vector.y);
    Vec2f unitVector = vector.sdiv(length);
    return unitVector;
  }
}
