package jmhao.game;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.io.IOException;

import cs195n.Vec2i;
import jmhao.engine.Screen;
import jmhao.engine.Application;
import jmhao.game.UIElements.GameMessage;
import jmhao.game.UIElements.QuitGameBar;
import jmhao.game.UIElements.StartMenuBar;
/**
 * MenuScreen for displaying menu
 * @author jmhao
 *
 */
public class OverScreen extends Screen{
  Application myApp;
  int hoverSelect;
  Vec2i currScreenSize;
  StartMenuBar StartMenu;
  QuitGameBar quitGame;
  GameMessage titleMessage;
  /**
   * MenuScreen constructor
   * @param a Application reference
   * @param message message to be displayed
   * @param hover hover rectangle option
   * @param screenSize size of screen
   */
  public OverScreen(Application a, String message, int hover, Vec2i screenSize) {
    myApp = a;
    hoverSelect = hover;
    currScreenSize = myApp.getFrame();
    //currScreenSize = screenSize;
    StartMenu = new StartMenuBar(currScreenSize);
    quitGame = new QuitGameBar(currScreenSize);
    titleMessage = new GameMessage(currScreenSize, message);
    titleMessage.setDimensions(.25f, .1f);
    if(hoverSelect == 0) {
      StartMenu.setSelected(true);
      quitGame.setSelected(false);
    } else {
      StartMenu.setSelected(false);
      quitGame.setSelected(true);
    }
  }
  @Override
  protected void onTick(long nanosSincePreviousTick) {
    
  }
  @Override
  protected void onDraw(Graphics2D g) {
    g.drawImage(Final.bgPng, -50, 0, currScreenSize.x+100, currScreenSize.y, 0, 0, 2000, 1000, null);
    
    if(hoverSelect == 0) {
      StartMenu.setSelected(true);
      quitGame.setSelected(false);
    } else {
      StartMenu.setSelected(false);
      quitGame.setSelected(true);
    }
    StartMenu.drawElement(g);
    quitGame.drawElement(g);
    titleMessage.drawElement(g);    
  }
  @Override
  protected void onKeyTyped(KeyEvent e) {
  }
  @Override
  protected void onKeyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      if(hoverSelect == 1) {
        hoverSelect = 0;
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      if(hoverSelect == 0) {
        hoverSelect = 1;
      }
    }
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      if(hoverSelect == 0) {
        myApp.setScreen(new MenuScreen(myApp, "Welome to Super Smash Legends! Use up and down, and primary attack to select a character, Enter to start", 0, new Vec2i(960, 540), false));
        }
      else {
        myApp.shutdown();
      }
    }
  }
  @Override
  protected void onKeyReleased(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  protected void onMouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    
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
    StartMenu.setSize(newSize);
    quitGame.setSize(newSize);
    titleMessage.setSize(newSize);
  }
}
