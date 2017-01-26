package jmhao.engine.Shape;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Polygon extends Shape {
  Vec2i startLocation;
  ArrayList<Vec2f> points = new ArrayList<Vec2f>();
  ArrayList<Vec2f> vectors = new ArrayList<Vec2f>();
  ArrayList<Vec2f> normalVectors = new ArrayList<Vec2f>();
  Color color = Color.black;
  GeneralPath polygon;
  Vec2i center;

  public ArrayList<Vec2f> getPoints() {
    return points;
  }

  public ArrayList<Vec2f> getVectors() {
    return normalVectors;
  }

  public Polygon(Vec2i startLoc, Vec2f... myPoints) {
    Vec2f min = new Vec2f(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    Vec2f max = new Vec2f(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
    startLocation = startLoc;
    for (Vec2f point : myPoints) {
      if (point.x < min.x) {
        min = new Vec2f(point.x, min.y);
      }
      if (point.y < min.y) {
        min = new Vec2f(min.x, point.y);
      }
      if (point.x > max.x) {
        max = new Vec2f(point.x, max.y);
      }
      if (point.y > max.y) {
        max = new Vec2f(max.x, point.y);
      }
      points.add(point);
    }
    center = new Vec2i((int) (min.x + max.x) / 2, (int) (min.y + max.y) / 2);
    Vec2f first, second;

    float length;
    for (int i = 0; i < points.size(); i++) {
      first = points.get(i);
      if (i != points.size() - 1) {
        second = points.get(i + 1);
      } else {
        second = points.get(0);
      }
      Vec2f vector = new Vec2f(second.x - first.x, second.y - first.y);
      Vec2f unitVector = unit(vector);
      Vec2f normVector = new Vec2f(-unitVector.y, unitVector.x);
      vectors.add(vector);
      normalVectors.add(normVector);
    }
    polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, points.size());
    polygon.moveTo(points.get(0).x, points.get(0).y);
    for (int i = 1; i < points.size(); i++) {
      polygon.lineTo(points.get(i).x, points.get(i).y);
    }
    polygon.closePath();
  }

  public Vec2i getCenter() {
    Vec2f min = new Vec2f(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
    Vec2f max = new Vec2f(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
    for (Vec2f point : points) {
      if (point.x < min.x) {
        min = new Vec2f(point.x, min.y);
      }
      if (point.y < min.y) {
        min = new Vec2f(min.x, point.y);
      }
      if (point.x > max.x) {
        max = new Vec2f(point.x, max.y);
      }
      if (point.y > max.y) {
        max = new Vec2f(max.x, point.y);
      }
    }
    center = new Vec2i((int) (min.x + max.x) / 2, (int) (min.y + max.y) / 2);
    return center;
  }

  @Override
  public void setLocation(Vec2i newLoc) {
    Vec2f changePos = new Vec2f(newLoc.x - startLocation.x, newLoc.y
        - startLocation.y);
    startLocation = newLoc;
    for (int i = 0; i < points.size(); i++) {
      Vec2f point = points.get(i);
      point = point.plus(changePos);
      points.set(i, point);
    }
    polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, points.size());
    polygon.moveTo(points.get(0).x, points.get(0).y);
    for (int i = 1; i < points.size(); i++) {
      polygon.lineTo(points.get(i).x, points.get(i).y);
    }
    polygon.closePath();
  }

  @Override
  public void setColor(Color newC) {
    color = newC;
  }

  @Override
  public void drawElement(Graphics2D g) {
    g.setColor(color);
    g.fill(polygon);
    g.setColor(Color.black);
  }

  @Override
  public boolean isSelected(Point p) {
    return polygon.contains(p);
  }

  @Override
  public Vec2f collides(Shape o) {
    Vec2f ret = o.collidesPolygon(this);
    if (ret != null) {
      ret = ret.smult(-1);
    }
    return ret;
  }

  @Override
  public Vec2f collidesCircle(Circle c) {
    Vec2i center = c.getCenter();
    Vec2f closest = null;
    float closestDistance = Float.POSITIVE_INFINITY;
    float currDist;
    Float minMagnitude = Float.POSITIVE_INFINITY;
    Vec2f mtv = null;
    Vec2f centerVec;
    Vec2f unitVec;
    ArrayList<Vec2f> circles = new ArrayList<>();
    Vec2f circleOne, circleTwo;
    for (Vec2f point : points) {
      currDist = point.dist(center.x, center.y);
      if (currDist < closestDistance) {
        closestDistance = currDist;
        closest = point;
      }
    }
    centerVec = closest.minus(center.x, center.y);
    unitVec = unit(centerVec);
    centerVec = unitVec.smult((float) c.getRad());
    circleOne = new Vec2f(centerVec.x + center.x, centerVec.y + center.y);
    circleTwo = new Vec2f(-centerVec.x + center.x, -centerVec.y + center.y);
    circles.add(circleOne);
    circles.add(circleTwo);
    for (int i = 0; i < normalVectors.size(); i++) {
      ArrayList<Vec2f> circles2 = new ArrayList<>();
      Vec2f centerVec2 = normalVectors.get(i).smult((float) c.getRad());
      Vec2f circleOne2 = new Vec2f(centerVec2.x + center.x, centerVec2.y + center.y);
      Vec2f circleTwo2 = new Vec2f(-centerVec2.x + center.x, -centerVec2.y + center.y);
      circles2.add(circleOne2);
      circles2.add(circleTwo2);
      Float mtv1d = checkOverlapProjection(normalVectors.get(i), circles2);
      if (mtv1d == null) {
        return null;
      }
      if (Math.abs(mtv1d) < minMagnitude) {
        minMagnitude = Math.abs(mtv1d);
        mtv = normalVectors.get(i).smult(mtv1d);
      }
    }
    Float mtv1d = checkOverlapProjection(unitVec, circles);
    if (mtv1d == null) {
      return null;
    }
    if (Math.abs(mtv1d) < minMagnitude) {
      minMagnitude = Math.abs(mtv1d);
      mtv = unitVec.smult(mtv1d);
    }
    if (mtv == null) {
      return mtv;
    } else {
      if (checkMTV(mtv, getCenter(), c.getCenter())) {
        return mtv;
      } else {
        return mtv.smult(-1);
      }
    }
  }

  public Float checkOverlapProjection(Vec2f vector, ArrayList<Vec2f> myPoints) {
    Vec2f minMax = new Vec2f(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY);
    Vec2f myMinMax = new Vec2f(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY);
    for (Vec2f point : points) {
      myMinMax = overlapProjection(vector, point, myMinMax);
    }
    for (Vec2f point : myPoints) {
      minMax = overlapProjection(vector, point, minMax);
    }
    return intervalMTV(myMinMax, minMax);
    // return checkOverlap(minMax, myMinMax);
  }

  public boolean checkOverlap(Vec2f one, Vec2f two) {
    return (one.x <= two.y) && (two.x <= one.y);
  }

  public Vec2f overlapProjection(Vec2f vector, Vec2f point, Vec2f minMax) {
    float f = vector.x * point.x + vector.y * point.y;
    if (f < minMax.x)
      minMax = new Vec2f(f, minMax.y);
    if (f > minMax.y) {
      minMax = new Vec2f(minMax.x, f);
    }
    return minMax;
  }

  @Override
  public Vec2f collidesRect(Rectangle r) {
    Vec2f xMinMax = new Vec2f((float) r.xMin(), (float) r.xMax());
    Vec2f yMinMax = new Vec2f((float) r.yMin(), (float) r.yMax());
    Vec2f xAxis = new Vec2f(1, 0);
    Vec2f yAxis = new Vec2f(0, 1);
    ArrayList<Vec2f> axis = new ArrayList<Vec2f>();
    axis.add(xAxis);
    axis.add(yAxis);
    Vec2f myXMinMax = new Vec2f(Float.POSITIVE_INFINITY,
        Float.NEGATIVE_INFINITY);
    Vec2f myYMinMax = new Vec2f(Float.POSITIVE_INFINITY,
        Float.NEGATIVE_INFINITY);
    ArrayList<Vec2f> rectSides = new ArrayList<Vec2f>();
    rectSides.add(new Vec2f((float) r.xMin(), (float) r.yMin()));
    rectSides.add(new Vec2f((float) r.xMin(), (float) r.yMax()));
    rectSides.add(new Vec2f((float) r.xMax(), (float) r.yMin()));
    rectSides.add(new Vec2f((float) r.xMax(), (float) r.yMax()));
    Float minMagnitude = Float.POSITIVE_INFINITY;
    Vec2f mtv = null;
    for (int i = 0; i < normalVectors.size(); i++) {
      Float mtv1d = checkOverlapProjection(normalVectors.get(i), rectSides);
      if (mtv1d == null) {
        return null;
      }
      if (Math.abs(mtv1d) < minMagnitude) {
        minMagnitude = Math.abs(mtv1d);
        mtv = normalVectors.get(i).smult(mtv1d).smult(-1);
      }
      /*
       * (!checkOverlapProjection(normalVectors.get(i),rectSides)) { return
       * false; }
       */
    }
    for (int i = 0; i < axis.size(); i++) {
      Float mtv1d = checkOverlapProjection(axis.get(i), rectSides);
      if (mtv1d == null) {
        return null;
      }
      if (Math.abs(mtv1d) < minMagnitude) {
        minMagnitude = Math.abs(mtv1d);
        mtv = axis.get(i).smult(mtv1d);
      }
    }
    if (mtv == null) {
      return mtv;
    } else {
      if (checkMTV(mtv, getCenter(), r.getCenter())) {
        return mtv;
      } else {
        return mtv.smult(-1);
      }
    }
    // return checkOverlap(myXMinMax, xMinMax) && checkOverlap(myYMinMax,
    // yMinMax);
  }

  @Override
  public Vec2f collidesComp(Comp m) {
    Vec2f totalMtv = null;
    ArrayList<Shape> shapes = m.getShapes();
    for (Shape s : shapes) {
      Vec2f curr = s.collidesPolygon(this);
      if (curr != null) {
        curr = curr.smult(-1);
        if (totalMtv == null) {
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
    Vec2f point = new Vec2f(p.x, p.y);
    Vec2f startPoint;
    Vec2f vector;
    float cross;
    for (int i = 0; i < vectors.size(); i++) {
      startPoint = point;
      startPoint = startPoint.minus(points.get(i));
      vector = vectors.get(i);
      cross = vector.x * startPoint.y - vector.y * startPoint.x;
      if (cross > 0) {
        return false;
      }
    }
    return true;
  }

  @Override
  public Vec2i getLocation() {
    return startLocation;
  }

  @Override
  public Vec2f collidesPolygon(Polygon p) {
    ArrayList<Vec2f> thosePoints = p.getPoints();
    ArrayList<Vec2f> thoseVecs = p.getVectors();
    Float minMagnitude = Float.POSITIVE_INFINITY;
    Vec2f mtv = null;
    for (int i = 0; i < normalVectors.size(); i++) {
      Float mtv1d = checkOverlapProjection(normalVectors.get(i), thosePoints);
      if (mtv1d == null) {
        return null;
      }
      if (Math.abs(mtv1d) < minMagnitude) {
        minMagnitude = Math.abs(mtv1d);
        mtv = normalVectors.get(i).smult(mtv1d).smult(-1);
      }
    }
    for (int i = 0; i < thoseVecs.size(); i++) {
      Float mtv1d = checkOverlapProjection(thoseVecs.get(i), thosePoints);
      if (mtv1d == null) {
        return null;
      }
      if (Math.abs(mtv1d) < minMagnitude) {
        minMagnitude = Math.abs(mtv1d);
        mtv = normalVectors.get(i).smult(mtv1d);
      }
    }
    if (mtv == null) {
      return mtv;
    } else {
      if (checkMTV(mtv, getCenter(), p.getCenter())) {
        return mtv;
      } else {
        return mtv.smult(-1);
      }
    }
  }

  @Override
  public Vec2i checkRay(Vec2i startPos, Vec2f vec) {
    Vec2f first, second, normal;
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
