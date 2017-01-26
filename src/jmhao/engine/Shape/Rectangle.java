package jmhao.engine.Shape;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Rectangle extends Shape {
  Vec2i startLocation;
  Color c = Color.BLACK;
  Rectangle2D rect;
  Vec2i center;
  double width, length;
  public Rectangle(Vec2i startLoc, double w, double l) {
    startLocation = startLoc;
    width = w;
    length = l;
    center = new Vec2i((int)(startLoc.x + width/2), (int)(startLoc.y + length/2));
    rect = new Rectangle2D.Double(startLocation.x, startLocation.y, width, length);
  }
  @Override
  public Vec2i getCenter() {
    center = new Vec2i((int)(startLocation.x + width/2), (int)(startLocation.y + length/2));
    return center;
  }
  public void setSize(double newW, double newL) {
    width = newW;
    length = newL;
    rect = new Rectangle2D.Double(startLocation.x, startLocation.y, width, length);
  }
  @Override
  public void setLocation(Vec2i newLoc) {
    startLocation = newLoc;
    rect = new Rectangle2D.Double(startLocation.x, startLocation.y, width, length);
  }
  @Override
  public void setColor(Color newC) {
    c = newC;
  }
  @Override
  public void drawElement(Graphics2D g) {
    Color temp = g.getColor();
    g.setColor(c);
    g.fill(rect);
    g.setColor(temp);
  }
  @Override
  public boolean isSelected(Point p) {
    return rect.contains(p);
  }
  @Override
  public Vec2f collides(Shape o) {
    Vec2f ret = o.collidesRect(this);
    if(ret != null) {
      ret = ret.smult(-1);
    }
    return ret;
  }
  public double getWidth() {
    return width;
  }
  public double getLength() {
    return length;
  }
  public double xMin() {
    return startLocation.x;
  }
  public double xMax() {
    return startLocation.x + width;
  }
  public double yMin() {
    return startLocation.y;
  }
  public double yMax() {
    return startLocation.y + length;
  }
  public Vec2i getStart() {
    return startLocation;
  }
  @Override
  public Vec2f collidesCircle(Circle c) {
    Vec2f ret = c.collidesRect(this);
    if(ret != null) {
      ret = ret.smult(-1);
    }
    return ret;
  }
  @Override
  public boolean checkPoint(Vec2i p) {
    boolean xAxis = (xMin() <= p.x) && (xMax() >= p.x);
    boolean yAxis = (yMin() <= p.y) && (yMax() >= p.y);
    return xAxis && yAxis;
  }
  @Override
  public Vec2f collidesRect(Rectangle r) {
    boolean xAxis = (xMin() <= r.xMax()) && (xMax() >= r.xMin());
    boolean yAxis = (yMin() <= r.yMax()) && (yMax() >= r.yMin());
    Vec2f mtv = null;
    Float minMagnitude = Float.POSITIVE_INFINITY;
    if(xAxis && yAxis) {
      Float xMin = (float) (r.xMax() - xMin());
      mtv = new Vec2f(xMin, 0);
      minMagnitude = xMin;
      Float xMax = (float) (xMax() - r.xMin());
      if(xMax < minMagnitude) {
        minMagnitude = xMax;
        mtv = new Vec2f(-xMax, 0);
      }
      Float yMin = (float) (r.yMax() - yMin());
      if(yMin < minMagnitude) {
        mtv = new Vec2f(0, yMin);
        minMagnitude = yMin;
      }
      Float yMax = (float) (yMax() - r.yMin());
      if(yMax < minMagnitude) {
        minMagnitude = yMax;
        mtv = new Vec2f(0, -yMax);
      }
    }
    if(mtv == null) {
      return mtv;
    } else {
      if(checkMTV(mtv, getCenter(), r.getCenter())) {
        return mtv;
      } else {
        return mtv.smult(-1);
      }
    }
    //return xAxis && yAxis;
  }
  @Override
  public Vec2f collidesComp(Comp m) {
    Vec2f totalMtv = null;
    ArrayList<Shape> shapes = m.getShapes();
    for(Shape s : shapes) {
      Vec2f curr = s.collidesRect(this);
      if(curr != null) {
        curr = curr.smult(-1);
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
  public Vec2i getLocation() {
    return startLocation;
  }
  @Override
  public Vec2f collidesPolygon(Polygon p) {
    Vec2f ret = p.collidesRect(this);
    if(ret != null) {
      ret = ret.smult(-1);
    }
    return ret;
  }
  @Override
  public Vec2i checkRay(Vec2i startPos, Vec2f vec) {
    ArrayList<Vec2f> points = new ArrayList<Vec2f>();
    Vec2f firstPoint = new Vec2f(startLocation.x, startLocation.y);
    points.add(firstPoint);
    points.add(firstPoint.plus(new Vec2f((float)getWidth(), 0)));
    points.add(firstPoint.plus(new Vec2f((float)getWidth(), (float)getLength())));
    points.add(firstPoint.plus(new Vec2f(0, (float)getLength())));
    Vec2f first, second, normal;
    ArrayList<Vec2f> normalVectors = new ArrayList<Vec2f>();
    normalVectors.add(new Vec2f(0, 1));
    normalVectors.add(new Vec2f(-1, 0));
    normalVectors.add(new Vec2f(0, -1));
    normalVectors.add(new Vec2f(1, 0));
    float bestDist = Float.POSITIVE_INFINITY;
    Vec2i bestIntersect = null;
    for(int i = 0; i < points.size(); i++) {
      first = points.get(i);
      if(i == points.size()-1) {
        second = points.get(0);
      } else {
        second = points.get(i+1);
      }
      Vec2i intersect = intersectEdge(startPos, vec, first, second, normalVectors.get(i));
      if(intersect != null) {
        Vec2i diff = intersect.minus(startPos);
        float dist = dist(new Vec2f(diff.x, diff.y));
        if(dist < bestDist && dist >= 0) {
          bestDist = dist;
          bestIntersect = intersect;
        }
      }
    }
    return bestIntersect;
  }
}
