package jmhao.game;

import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import jmhao.engine.Screen;
import jmhao.engine.Entity.Entity;
import jmhao.engine.Shape.Circle;
import jmhao.engine.Application;
import jmhao.game.UIElements.DebugWorld;
import jmhao.game.UIElements.TestCircle;
import jmhao.game.UIElements.TestRect;

public class DebugScreen extends Screen{
  Application myApp;
  Vec2i currScreenSize;
  DebugWorld myDW;
  public DebugScreen(Application a, Vec2i screenSize) {
    myApp = a;
    currScreenSize = screenSize;
    myDW = new DebugWorld(currScreenSize);
  }
  @Override
  protected void onTick(long nanosSincePreviousTick) {
    myDW.getTick(nanosSincePreviousTick/1000000);
  }

  @Override
  protected void onDraw(Graphics2D g) {
    myDW.drawElement(g);
  }

  @Override
  protected void onKeyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void onKeyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void onKeyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void onMouseClicked(MouseEvent e) {
  }

  @Override
  protected void onMousePressed(MouseEvent e) {
    myDW.onMousePressed(e);
  }

  @Override
  protected void onMouseReleased(MouseEvent e) {
    myDW.onMouseReleased(e);
  }

  @Override
  protected void onMouseDragged(MouseEvent e) {
    myDW.onMouseDragged(e);
  }

  @Override
  protected void onMouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  protected void onMouseWheelMoved(MouseWheelEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  protected void onResize(Vec2i newSize) {
    // TODO Auto-generated method stub
    
  }

}
