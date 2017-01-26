package jmhao.engine.Shape;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Circle extends Shape{
  Vec2i startLocation;
  Color c = Color.BLACK;
  Ellipse2D circle;
  double radius;
  public Circle(Vec2i startLoc, double rad) {
    startLocation = startLoc;
    radius = rad;
    circle = new Ellipse2D.Double(startLocation.x, startLocation.y, radius*2, radius*2);
  }
  public void setSize(double newRad) {
    radius = newRad;
    circle = new Ellipse2D.Double(startLocation.x, startLocation.y, radius*2, radius*2);
  }
  @Override
  public void setLocation(Vec2i newLoc) {
    startLocation = newLoc;
    circle = new Ellipse2D.Double(startLocation.x, startLocation.y, radius*2, radius*2);
  }
  @Override
  public void setColor(Color newC) {
    c = newC;
  }
  @Override
  public void drawElement(Graphics2D g) {
    Color temp = g.getColor();
    g.setColor(c);
    g.fill(circle);
    g.setColor(temp);
  }
  @Override
  public boolean isSelected(Point p) {
    return circle.contains(p);
  }
  public double getRad() {
    return radius;
  }
  @Override
  public Vec2i getCenter() {
    return new Vec2i((int)(startLocation.x + radius), (int)(startLocation.y + radius));
  }
  @Override
  public boolean checkPoint(Vec2i p) {
    double distance = (getCenter().x - p.x)*(getCenter().x - p.x);
    distance += (getCenter().y - p.y)*(getCenter().y - p.y);
    double thisRad = radius*radius;
    return distance <= thisRad;
  }
  @Override
  public Vec2f collidesComp(Comp m) {
    Vec2f totalMtv = null;
    ArrayList<Shape> shapes = m.getShapes();
    for(Shape s : shapes) {
      Vec2f curr = s.collidesCircle(this);
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
  public Vec2f collides(Shape o) {
    Vec2f ret = o.collidesCircle(this);
    if(ret != null) {
      ret = ret.smult(-1);
    }
    return ret;
  }
  @Override
  public Vec2f collidesCircle(Circle c) {
    double distance = (getCenter().x - c.getCenter().x)*(getCenter().x - c.getCenter().x);
    distance += (getCenter().y - c.getCenter().y)*(getCenter().y - c.getCenter().y);
    double radiusDiff = (radius + c.getRad());
    distance = Math.sqrt(distance);
    Float magnitude = (float) (radiusDiff - distance);
    if(magnitude >= 0) {
      Vec2f axis = new Vec2f(c.getCenter().x - getCenter().x, c.getCenter().y - getCenter().y);
      axis = unit(axis).smult(-1);
      axis = axis.smult(magnitude);
      return axis;
    }
    return null;
  }
  @Override
  public Vec2f collidesRect(Rectangle r) {
    boolean withinX = (getCenter().x >= r.xMin()) && (getCenter().x <= r.xMax());
    boolean withinY = (getCenter().y >= r.yMin()) && (getCenter().y <= r.yMax());
    float minMagnitude = Float.POSITIVE_INFINITY;
    Vec2f mtv = null;
    if(withinX) {
      if(withinY) {
        minMagnitude = (float) (getCenter().x - r.xMin() + radius);
        mtv = new Vec2f(-minMagnitude, 0);
        float xMax = (float) (r.xMax() - getCenter().x + radius);
        if(xMax < minMagnitude) {
          minMagnitude = xMax;
          mtv = new Vec2f(minMagnitude, 0);
        }
        float yMin = (float) (getCenter().y - r.yMin() + radius);
        if(yMin < minMagnitude) {
          minMagnitude = yMin;
          mtv = new Vec2f(0, -minMagnitude);
        }
        float yMax = (float) (r.yMax() - getCenter().y + radius);
        if(yMax < minMagnitude) {
          minMagnitude = yMax;
          mtv = new Vec2f(0, minMagnitude);
        }
        return mtv;
      } else {
        if((r.yMin() <= (getCenter().y + radius) && r.yMin() >= (getCenter().y))) {
          float yMin = (float) (getCenter().y + radius - r.yMin());
          if(yMin < minMagnitude) {
            minMagnitude = yMin;
            mtv = new Vec2f(0, -minMagnitude);
          }
        } else if ((r.yMax() >= (getCenter().y - radius) && r.yMax() <= (getCenter().y))) {
          float yMax = (float) (r.yMax() - (getCenter().y-radius));
          if(yMax < minMagnitude) {
            minMagnitude = yMax;
            mtv = new Vec2f(0, minMagnitude);
          }
        } else {
          mtv = null;
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
        //return ((r.yMin() <= (getCenter().y + radius) && r.yMin() >= (getCenter().y)) || (r.yMax() >= (getCenter().y - radius) && r.yMax() <= (getCenter().y)));
      }
    } else {
      if(withinY) {
        if((r.xMin() <= (getCenter().x + radius) && r.xMin() >= (getCenter().x))) {
          float xMin = (float) (getCenter().x + radius - r.xMin());
          if(xMin < minMagnitude) {
            minMagnitude = xMin;
            mtv = new Vec2f(-minMagnitude, 0);
          }
        } else if ((r.xMax() >= (getCenter().x - radius) && r.xMax() <= (getCenter().x))) {
          float xMax = (float) (r.xMax() - (getCenter().x-radius));
          if(xMax < minMagnitude) {
            minMagnitude = xMax;
            mtv = new Vec2f(minMagnitude, 0);
          }
        } else {
          mtv = null;
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
        //return ((r.xMin() <= (getCenter().x + radius) && r.xMin() >= (getCenter().x)) || (r.xMax() >= (getCenter().x - radius) && r.xMax() <= (getCenter().x)));
        } else {
        Vec2i start = r.getStart();
        if(r.xMin() >= getCenter().x) {
          if(r.yMin() >= getCenter().y) {
            if(checkPoint(start)) {
              Vec2i dir = start.minus(getCenter());
              Vec2f unitDir = unit(new Vec2f(dir.x, dir.y));
              unitDir = unitDir.smult((float)radius);
              dir = dir.minus(new Vec2i((int)unitDir.x, (int)unitDir.y));
              mtv = new Vec2f(dir.x, dir.y);
              mtv = mtv.smult(-1);
              if(mtv == null) {
                return mtv;
              } else {
                if(checkMTV(mtv, getCenter(), r.getCenter())) {
                  return mtv;
                } else {
                  return mtv.smult(-1);
                }
              }
            } else {
              return null;
            }
          } else {
            if(checkPoint(new Vec2i(start.x,(int)(start.y + r.getLength())))) {
              Vec2i dir = new Vec2i(start.x,(int)(start.y + r.getLength())).minus(getCenter());
              Vec2f unitDir = unit(new Vec2f(dir.x, dir.y));
              unitDir = unitDir.smult((float)radius);
              dir = dir.minus(new Vec2i((int)unitDir.x, (int)unitDir.y));
              mtv = new Vec2f(dir.x, dir.y);
              mtv = mtv.smult(-1);
              if(mtv == null) {
                return mtv;
              } else {
                if(checkMTV(mtv, getCenter(), r.getCenter())) {
                  return mtv;
                } else {
                  return mtv.smult(-1);
                }
              }
            } else {
              return null;
            }
          }
        } else {
          if(r.yMin() >= getCenter().y) {
            if(checkPoint(new Vec2i((int)(start.x + r.getWidth()), start.y))) {
              Vec2i dir = new Vec2i((int)(start.x + r.getWidth()), start.y).minus(getCenter());
              Vec2f unitDir = unit(new Vec2f(dir.x, dir.y));
              unitDir = unitDir.smult((float)radius);
              dir = dir.minus(new Vec2i((int)unitDir.x, (int)unitDir.y));
              mtv = new Vec2f(dir.x, dir.y);
              mtv = mtv.smult(-1);
              if(mtv == null) {
                return mtv;
              } else {
                if(checkMTV(mtv, getCenter(), r.getCenter())) {
                  return mtv;
                } else {
                  return mtv.smult(-1);
                }
              }
            } else {
              return null;
            }
          } else {
            if(checkPoint(new Vec2i((int)(start.x + r.getWidth()),(int)(start.y + r.getLength())))) {
              Vec2i dir = new Vec2i((int)(start.x + r.getWidth()),(int)(start.y + r.getLength())).minus(getCenter());
              Vec2f unitDir = unit(new Vec2f(dir.x, dir.y));
              unitDir = unitDir.smult((float)radius);
              dir = dir.minus(new Vec2i((int)unitDir.x, (int)unitDir.y));
              mtv = new Vec2f(dir.x, dir.y);
              mtv = mtv.smult(-1);
              if(mtv == null) {
                return mtv;
              } else {
                if(checkMTV(mtv, getCenter(), r.getCenter())) {
                  return mtv;
                } else {
                  return mtv.smult(-1);
                }
              }
            } else {
              return null;
            }
          }
        }
      }
    }
  }
  @Override
  public Vec2i getLocation() {
    return startLocation;
  }
  @Override
  public Vec2f collidesPolygon(Polygon p) {
    Vec2f ret = p.collidesCircle(this);
    if(ret != null) {
      ret = ret.smult(-1);
    }
    return ret;
  }
  @Override
  public Vec2i checkRay(Vec2i startPos, Vec2f vec) {
    boolean inside = checkPoint(startPos);
    if(!inside) {
      Vec2i startDiff = getCenter().minus(startPos);
      Vec2f floatStartDiff = new Vec2f(startDiff.x, startDiff.y);
      float projectedFloat = project(floatStartDiff, vec);
      if(projectedFloat > 0) {
        Vec2f projected = (vec.smult(projectedFloat));
        Vec2i projectedInt = new Vec2i((int)projected.x, (int)projected.y);
        projectedInt = projectedInt.plus(startPos);
        
        if((checkPoint(projectedInt))) {
          float f = distBetween(projectedInt, getCenter());
          float dist = (float)Math.sqrt(radius*radius - f*f);
          Vec2f distVec = vec.smult(dist);
          Vec2i distVecInt = new Vec2i((int)distVec.x, (int)distVec.y);
          Vec2i answer = projectedInt.minus(distVecInt);
          return answer;
        }
      }
    } else {
      Vec2i startDiff = getCenter().minus(startPos);
      Vec2f floatStartDiff = new Vec2f(startDiff.x, startDiff.y);
      float projectedFloat = project(floatStartDiff, vec);
      if(projectedFloat > 0) {
        Vec2f projected = (vec.smult(projectedFloat));
        Vec2i projectedInt = new Vec2i((int)projected.x, (int)projected.y);
        projectedInt = projectedInt.plus(startPos);
        float f = distBetween(projectedInt, getCenter());
        float dist = (float)Math.sqrt(radius*radius - f*f);
        Vec2f distVec = vec.smult(dist);
        Vec2i distVecInt = new Vec2i((int)distVec.x, (int)distVec.y);
        Vec2i answer = projectedInt.plus(distVecInt);
        return answer;
      }
    }
    return null;
  }
}
