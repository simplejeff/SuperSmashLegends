package jmhao.game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.IOException;

import cs195n.Vec2i;
import jmhao.engine.Screen;
import jmhao.engine.Application;
import jmhao.game.UIElements.CharacterSelect;
import jmhao.game.UIElements.GameMessage;
import jmhao.game.UIElements.MusicPage;
import jmhao.game.UIElements.QuitGameBar;
import jmhao.game.UIElements.StartGameBar;
import jmhao.game.UIElements.StartGameBar2;
/**
 * MenuScreen for displaying menu
 * @author jmhao
 *
 */
public class MenuScreen extends Screen{
  Application myApp;
  int hoverSelect;
  Vec2i currScreenSize;
  CharacterSelect char1;
  CharacterSelect char2;
  boolean char1Ready = false;
  boolean char2Ready = false;
  QuitGameBar quitGame;
  GameMessage titleMessage;
  MusicPage music;
  /**
   * MenuScreen constructor
   * @param a Application reference
   * @param message message to be displayed
   * @param hover hover rectangle option
   * @param screenSize size of screen
   */
  public MenuScreen(Application a, String message, int hover, Vec2i screenSize, boolean first) {
    myApp = a;
    hoverSelect = hover;
    if(first) {
      currScreenSize = screenSize;
    } else {
      currScreenSize = myApp.getFrame();
    }
    char1 = new CharacterSelect(currScreenSize, .01f, 0);
    char2 = new CharacterSelect(currScreenSize, .65f, 1);
    titleMessage = new GameMessage(currScreenSize, message);
    titleMessage.setDimensions(.03f, .1f);
    music = new MusicPage(currScreenSize);
  }
  @Override
  protected void onTick(long nanosSincePreviousTick) {
    
  }
  @Override
  protected void onDraw(Graphics2D g) {
    g.drawImage(Final.bgPng, -50, 0, currScreenSize.x+100, currScreenSize.y, 0, 0, 2000, 1000, null);
    g.drawImage(Final.logoPng, (int)(currScreenSize.x*.18), (int)(currScreenSize.y*.05),(int)(currScreenSize.x*.713333), (int)(currScreenSize.y*.583333), 0, 0,1366, 768, null);
    char1.drawElement(g);
    char2.drawElement(g);
    titleMessage.drawElement(g);    
    music.drawElement(g);
  }
  @Override
  protected void onKeyTyped(KeyEvent e) {
  }
  @Override
  protected void onKeyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      char2.decrementCharacter();
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      char2.incrementCharacter();
    }
    if (e.getKeyCode() == KeyEvent.VK_W) {
      char1.decrementCharacter();
    }
    if (e.getKeyCode() == KeyEvent.VK_S) {
      char1.incrementCharacter();
    }
    if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
      char2Ready = true;
      char2.setSelected(true);
    }
    if (e.getKeyCode() == KeyEvent.VK_G) {
      char1Ready = true;
      char1.setSelected(true);
    }
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      boolean mapLoaded = true;
      int firstChar = char1.character();
      int secondChar = char2.character();
      if(char1Ready && char2Ready) {
        myApp.setScreen(new GameScreen(myApp, currScreenSize, firstChar, secondChar));
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_Q) {
      myApp.shutdown();
    }
  }
  @Override
  protected void onKeyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  protected void onMouseClicked(MouseEvent e) {
    Point p = e.getPoint();
    music.checkClick(p);
  }
  @Override
  protected void onMousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  protected void onMouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  protected void onMouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
    
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
    currScreenSize = newSize;
    char1.setSize(newSize);
    char2.setSize(newSize);
    titleMessage.setSize(newSize);
    music.setSize(newSize);
  }
}
