package jmhao.engine.Viewport;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import jmhao.engine.UIElement;

public class Viewport extends UIElement{
  protected int width, length;
  protected float xFactor = .05f;
  protected float yFactor = .1f;
  protected float minScaleFactor;
  protected float maxScaleFactor = 250.0f;
  protected float xSideFactor = .9f;
  protected float ySideFactor = .45f;
  protected float scaleFactor = 40.0f;
  protected Viewportable viewPortable;
  protected Vec2f midPoint;
  protected Rectangle2D boardFrame;
  protected Point mousePos;
  protected Vec2f coords, sides, shiftFactor;
  protected boolean clicked = false;
  protected Vec2f previousClick;
  public Viewport(Viewportable bg, Vec2i startPoint) {
    width = bg.getWidth();
    length = bg.getLength();
    viewPortable = bg;
    coords = new Vec2f(0, 0);
    sides = new Vec2f(startPoint.x, startPoint.y);
   // coords = new Vec2f(startPoint.x * xFactor, startPoint.y * yFactor);
    //sides = new Vec2f(startPoint.x * xSideFactor, startPoint.x * ySideFactor);
    shiftFactor = new Vec2f(0f, 0f);
    scaleFactor = sides.x/(width);//*.5f);
    boardFrame = new Rectangle2D.Float(coords.x, coords.y, sides.x, sides.y);
    midPoint = new Vec2f(sides.x*.5f, sides.y*.5f-300);
    
  }
  /**
   * setClicked sets parameters if the board is clicked on
   * @param isClicked boolean of being clicked or not
   * @param e the mouseevent of the click
   */
  public void setClicked(boolean isClicked, MouseEvent e){
    if(isClicked && !clicked) {
      previousClick = new Vec2f(e.getX(), e.getY());
    }
    if(!isClicked){
      previousClick = null;
    }
    clicked = isClicked;
  }
  public void shiftPlayer(Vec2f playerLoc) {
    Vec2f newPlayerLoc = convertBack(playerLoc);
    Vec2f shift = midPoint.minus(newPlayerLoc);
    //Vec2f shift = newPlayerLoc.minus(midPoint);
    shift = new Vec2f(shift.x*.3f, shift.y*.3f);
    //setShift(shift);
  }
  public void setShift(Vec2f shift) {
    shiftFactor = shift;
  }
  /**
   * setPortLoc sets the location of our viewport
   * corresponding to the size of the GUI
   * @param loc viewport location corresponding to GUI
   */
  public void setPortLoc(Vec2f loc) {
    xFactor = loc.x;
    yFactor = loc.y;
  }
  /**
   * sets the max scale factor of our viewport
   * @param factor max factor to set
   */
  public void setMaxScaleFactor(float factor) {
    maxScaleFactor = factor;
  }
  /**
   * sets the sideFactor of our viewport
   * corresponding to the size of the GUI
   * @param f side factor corresponding to GUI
   */
  public void setSideFactor(Vec2f side) {
    xSideFactor = side.x;
    ySideFactor = side.y;
  }
  /**
   * checkShift checks if a shift is in bounds or not
   */
  public void checkShift(){
    if(shiftFactor.x > 0) {
      shiftFactor = new Vec2f(0f, shiftFactor.y);
    }
    if(shiftFactor.y > 0) {
      shiftFactor = new Vec2f(shiftFactor.x, 0f);
    }
    if(shiftFactor.x + width*scaleFactor < sides.x) {
      shiftFactor = new Vec2f(sides.x - width*scaleFactor, shiftFactor.y);
    }
    if(shiftFactor.y + length*scaleFactor < sides.y) {
      shiftFactor = new Vec2f(shiftFactor.x, sides.y - length*scaleFactor);
    }
  }
  /**
   * moveBoard moves the gameBoard on mouseDrag
   * @param e mouseevent of moving the board
   */
  public void moveBoard(MouseEvent e) {
    if(clicked) {
      Vec2f newClick = new Vec2f(e.getX(), e.getY());
      float newX;
      float newY;
      if(previousClick != null) {
        newX = shiftFactor.x + (newClick.x-previousClick.x);
        newY = shiftFactor.y + (newClick.y-previousClick.y);
        shiftFactor = new Vec2f(newX, newY);
       // checkShift();
      }
      previousClick = newClick;
    }
  }
  /**
   * scrollGame zooms in and out
   * @param e mouseevent of the mousewheel roll
   */
  public void scrollGame(MouseWheelEvent e) {
    float change = 1f - (float)e.getWheelRotation()*.05f;
    float prevScaleFactor = scaleFactor;
    scaleFactor*=change;
    //System.out.println("prev scale: " + prevScaleFactor + " new scale: " + scaleFactor);
    if(scaleFactor > maxScaleFactor) {
      scaleFactor = maxScaleFactor;
    }
    if(scaleFactor < minScaleFactor) {
      scaleFactor = minScaleFactor;
    }
    float xChange = shiftFactor.x + (prevScaleFactor-scaleFactor)*width*.5f;
    float yChange = shiftFactor.y + (prevScaleFactor-scaleFactor)*length*.5f;
    System.out.println("x shift: " + xChange + " y shift: " + yChange);
    shiftFactor = new Vec2f(xChange, yChange);
   // checkShift();
  }
  /**
   * returns the board position x y
   * @return position of board coords
   */
  public Vec2f boardPos(){
    return coords;
  }
  @Override
  public void setSize(Vec2i newSize) {
    //coords = new Vec2f(0, 0);
    sides = new Vec2f(newSize.x, newSize.y);
    boardFrame = new Rectangle2D.Float(coords.x, coords.y, sides.x, sides.y);
    scaleFactor = sides.x/width;
    midPoint = new Vec2f(sides.x*.5f, sides.y*.5f);
  }
  @Override
  public void drawElement(Graphics2D g) {
    g.draw(boardFrame);
    g.setColor(Color.black);
    //g.draw(boardFrame);
    g.clipRect((int)boardFrame.getX(), (int)boardFrame.getY(), (int)boardFrame.getWidth(), (int)boardFrame.getHeight());
    AffineTransform original = g.getTransform();
   // displayMatrix(g.getTransform());
   // g.setStroke(new BasicStroke(.05f));
    g.translate(shiftFactor.x, shiftFactor.y);
    g.scale(scaleFactor, scaleFactor);
    g.translate(coords.x/scaleFactor, coords.y/scaleFactor);
    viewPortable.drawElement(g);
    g.setTransform(original);
   // g.setStroke(new BasicStroke(5f));
  }
  /**
   * convertBack converts a point on the screen to game coordinates
   * @param original the original point on the screen
   * @return the converted point
   */
  public Vec2f convertBack(Vec2f original) {
    int xVal = (int)((original.x-coords.x - shiftFactor.x)/scaleFactor);
    int yVal = (int)((original.y-coords.y-shiftFactor.y)/scaleFactor);
    return new Vec2f(xVal, yVal);
    //return new Vec2f((original.x-coords.x - shiftFactor.x)/scaleFactor,(original.y-coords.y-shiftFactor.y)/scaleFactor);
  }
  @Override
  public boolean isSelected(Point p) {
    Vec2f newPoint = convertBack(new Vec2f((float)p.getX(),(float)p.getY()));
    mousePos = new Point();
    mousePos.setLocation(newPoint.x, newPoint.y); 
    return boardFrame.contains(p);
  }
  @Override
  public void setSelected(boolean s) {
    viewPortable.setSelected(mousePos);
  }
  /**
   * Get tick and send to game
   * @param millisSincePrevTick millis since prev tick
   */
  public void getTick(long millisSincePrevTick) {
    viewPortable.getTick(millisSincePrevTick);
  }
  /**
   * Get keypress and send to game
   * @param e key event of keypress
   */
  public void keyPressed(KeyEvent e) {
    viewPortable.keyPressed(e);
  }
}
