package jmhao.engine.Entity;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import jmhao.engine.Shape.Rectangle;

public class Timer<T extends PhysicsEntity<T>> extends PhysicsEntity<T>{
  private Output onTimerEnd;
  private Input doResetTimer;
  long startTime;
  long currTime = 0;
  int timer = 0;
  public Timer(long start) {
    Rectangle r = new Rectangle(new Vec2i(0,0),0,0);
    setShape(r);
    setStatic(true);
    startTime = start;
    onTimerEnd = new Output();
    doResetTimer = new Input() { public void run(HashMap<String, String> args){currTime = 0;}};
    addInput(doResetTimer, "doResetTimer");
    addOutput(onTimerEnd, "onTimerEnd"); 
  }
  
  @Override
  public void doCollide(T e) {
  }
  @Override
  public void onTick(long millisSincePreviousTick) {
    currTime += millisSincePreviousTick;
    if(currTime >= startTime) {
      onTimerEnd.run();
    }
    long timerTime = startTime - currTime;
    if(timerTime < 0) {
      timerTime = 0;
    }
    timer = (int)(timerTime/1000);
  }
  @Override
  public void changePos(Vec2i change) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void drawElement(Graphics2D g) {
    g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
    g.drawString(Integer.toString(timer), 600, 100);
    //g.drawString(player, (int)(box.getMinX() + 5), (int)(coords.y + sides.y * .2));
    //g.drawString(whichPlayer, (int)(coords.x + sides.x * .2), (int)(coords.y - sides.y * .5));
    g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
  }
  @Override
  public void onKeyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onKeyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void setSize(Vec2i newSize) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public boolean isSelected(Point p) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setSelected(boolean s) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public EntityType getEntity() {
    // TODO Auto-generated method stub
    return null;
  }

}
