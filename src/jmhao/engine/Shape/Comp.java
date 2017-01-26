package jmhao.engine.Shape;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Comp extends Shape {
  Vec2i startLocation;
  ArrayList<Shape> shapes = new ArrayList<Shape>();
  ArrayList<Vec2i> offset = new ArrayList<Vec2i>();
  public ArrayList<Shape> getShapes() {
    return shapes;
  }
  public Comp(Vec2i startLoc, Shape...myShapes) {
    startLocation = startLoc;
    for(Shape s : myShapes) {
      shapes.add(s);
    }
  }
  public void addOffsets(Vec2i...myOffsets) {
    for(Vec2i off : myOffsets) {
      offset.add(off);
    }
  }
  @Override
  public void setLocation(Vec2i newLoc) {
    startLocation = newLoc;
    Shape s;
    Vec2i thisOffset;
    for(int i = 0; i < shapes.size(); i++) {
      thisOffset = offset.get(i);
      s = shapes.get(i);
      s.setLocation(new Vec2i(newLoc.x + thisOffset.x, newLoc.y + thisOffset.y));
    }
  }

  @Override
  public void setColor(Color newC) {
    for(Shape s : shapes) {
      s.setColor(newC);
    }
  }

  @Override
  public void drawElement(Graphics2D g) {
    for(Shape s : shapes) {
      s.drawElement(g);
    }
  }

  @Override
  public boolean isSelected(Point p) {
    boolean result = false;
    Shape s;
    Vec2i thisOffset;
    for(int i = 0; i < shapes.size(); i++) {
      thisOffset = offset.get(i);
      s = shapes.get(i);
      p.setLocation(p.getX() + thisOffset.x, p.getY() + thisOffset.y);
      result |= s.isSelected(p);
      p.setLocation(p.getX() - thisOffset.x, p.getY() - thisOffset.y);
    }
    return result;
  }

  @Override
  public Vec2f collides(Shape o) {
    Vec2f totalMtv = null;
    ArrayList<Shape> shapes = getShapes();
    for(Shape s : shapes) {
      Vec2f curr = s.collides(o);
      if(curr != null) {
        if(totalMtv == null) {
          totalMtv = curr;
        } else {
          totalMtv = totalMtv.plus(curr);
        }
      }
    }
    return totalMtv;
  }

  @Override
  public Vec2f collidesCircle(Circle c) {
    Vec2f totalMtv = null;
    ArrayList<Shape> shapes = getShapes();
    for(Shape s : shapes) {
      Vec2f curr = s.collidesCircle(c);
      if(curr != null) {
        if(totalMtv == null) {
          totalMtv = curr;
        } else {
          totalMtv = totalMtv.plus(curr);
        }
      }
    }
    return totalMtv;
  }

  @Override
  public Vec2f collidesRect(Rectangle r) {
    Vec2f totalMtv = null;
    ArrayList<Shape> shapes = getShapes();
    for(Shape s : shapes) {
      Vec2f curr = s.collidesRect(r);
      if(curr != null) {
        if(totalMtv == null) {
          totalMtv = curr;
        } else {
          totalMtv = totalMtv.plus(curr);
        }
      }
    }
    return totalMtv;
  }

  @Override
  public Vec2f collidesComp(Comp m) {
    Vec2f totalMtv = null;
    ArrayList<Shape> shapes = getShapes();
    for(Shape s : shapes) {
      Vec2f curr = s.collidesComp(m);
      if(curr != null) {
        if(totalMtv == null) {
          totalMtv = curr;
        } else {
          totalMtv = totalMtv.plus(curr);
        }
      }
    }
    return totalMtv;
  }
  @Override
  public boolean checkPoint(Vec2i p) {
    boolean result = false;
    for(Shape s : shapes) {
      result |= s.checkPoint(p);
    }
    return result;
  }
  @Override
  public Vec2i getLocation() {
    return startLocation;
  }
  @Override
  public Vec2f collidesPolygon(Polygon p) {
    Vec2f totalMtv = null;
    ArrayList<Shape> shapes = getShapes();
    for(Shape s : shapes) {
      Vec2f curr = s.collidesPolygon(p);
      if(curr != null) {
        if(totalMtv == null) {
          totalMtv = curr;
        } else {
          totalMtv = totalMtv.plus(curr);
        }
      }
    }
    return totalMtv;
  }
  @Override
  public Vec2i getCenter() {
    Vec2i accumCenter = new Vec2i(0, 0);
    ArrayList<Shape> shapes = getShapes();
    int count = shapes.size();
    for(Shape s : shapes) {
      accumCenter = accumCenter.plus(s.getCenter());
    }
    accumCenter = accumCenter.sdiv(count);
    return accumCenter;
  }
  @Override
  public Vec2i checkRay(Vec2i startPos, Vec2f vec) {
    float bestDist = Float.POSITIVE_INFINITY;
    Vec2i bestIntersect = null;
    ArrayList<Shape> shapes = getShapes();
    for(Shape s : shapes) {
      Vec2i intersect = s.checkRay(startPos, vec);
      if(intersect != null) {
        Vec2i diff = intersect.minus(startPos);
        float dist = dist(new Vec2f(diff.x, diff.y));
        if(Math.abs(dist) < Math.abs(bestDist)) {
          bestDist = dist;
          bestIntersect = intersect;
        }
      }
    }
    return bestIntersect;
  }
}
