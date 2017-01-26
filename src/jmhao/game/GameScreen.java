  package jmhao.game;

  import cs195n.Vec2i;

  import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import jmhao.engine.Cutscene;
import jmhao.engine.Screen;
import jmhao.engine.Entity.Entity;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Shape.Circle;
import jmhao.engine.Sound.ClipPlayer;
import jmhao.engine.Application;
import jmhao.game.UIElements.DebugWorld;
import jmhao.game.UIElements.GameCutscene;
import jmhao.game.UIElements.GameViewport;
import jmhao.game.UIElements.GameWorld;
import jmhao.game.UIElements.GameWorldBuilder;
import jmhao.game.UIElements.TestCircle;
import jmhao.game.UIElements.TestRect;

public class GameScreen extends Screen {

    Application myApp;
    GameCutscene cutscene1;
    GameCutscene cutscene2;
    boolean scene1Finish = false;
    boolean scene2Finish = false;
    boolean gameStart = false;
    Vec2i currScreenSize;
    @SuppressWarnings("rawtypes")
    GameWorld myGW;
    GameViewport myVP;
    int char1, char2;
    @SuppressWarnings("rawtypes")
    public GameScreen(Application a, Vec2i screenSize, int char1, int char2) {
      this.char1 = char1;
      this.char2 = char2;
      cutscene1 = new GameCutscene(screenSize, "1", .1f, .1f, char1, getQuote(char1));
      cutscene1.setStart();
      cutscene2 = new GameCutscene(screenSize, "2", .1f, .6f, char2, getQuote(char2));
      myApp = a;
      currScreenSize = myApp.getFrame();
      //currScreenSize = screenSize;
      GameWorldBuilder gwb = new GameWorldBuilder(Final.ld, myApp, currScreenSize);
      gwb.parseEntities();
      gwb.parseConnections();
      myGW = gwb.getGameWorld();
      myGW.setChar1(char1);
      myGW.setChar2(char2);
      myVP = new GameViewport(myGW, currScreenSize);
    }
    public String getQuote(int character) {
      switch(character) {
      case 0:
        return "Never underestimate the power of the Scout's code! (Press primary attack)";
      case 1 :
        return "You belong in a museum! (Press primary attack)";
      case 2 :
        return "Follow the wind, but watch your back. (Press primary attack)";
      default :
        return "Sometimes, shield becomes smashing board. (Press primary attack)";
      }
    }
    @Override
    protected void onTick(long nanosSincePreviousTick) {
      if(gameStart) {
        myVP.shiftPlayer(myGW.playerPos());
        myGW.getTick(nanosSincePreviousTick/1000000);
        if(myGW.gameOver()) {
          myApp.setScreen(new OverScreen(myApp, "Game over!", 0, currScreenSize));
        }
        if(myGW.gameWon()) {
          myApp.setScreen(new OverScreen(myApp, "You Won!", 0, currScreenSize));
        }
      } else {
        if(!cutscene1.isFinished()) {
          cutscene1.onTick(nanosSincePreviousTick/400000);
        } else {
          scene1Finish = true;
        }
        if(cutscene2.isStarted()) {
          if(!cutscene2.isFinished()) {
            cutscene2.onTick(nanosSincePreviousTick/400000);
          } else {
            scene2Finish = true;
          }
        }
      }
    }

    @Override
    protected void onDraw(Graphics2D g) {
    /*  if(gameStart) {
        myVP.drawElement(g);
      } else {
        cutscene1.drawElement(g);
        cutscene2.drawElement(g);
      }*/
      myVP.drawElement(g);
      if(!gameStart) {
        cutscene1.drawElement(g);
        cutscene2.drawElement(g);
      }
    }

    @Override
    protected void onKeyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      
    }

    @Override
    protected void onKeyPressed(KeyEvent e) {
      if(gameStart) {
        myGW.onKeyPressed(e);
      } else {
        if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
          if(scene2Finish) {
            gameStart = true;
          }
        }
        if (e.getKeyCode() == KeyEvent.VK_G) {
          if(scene1Finish) {
            cutscene2.setStart();
          }
        }
      }
    }

    @Override
    protected void onKeyReleased(KeyEvent e) {
      if(gameStart) {
        myGW.onKeyReleased(e);
      }
    }

    @Override
    protected void onMouseClicked(MouseEvent e) {
    }

    @Override
    protected void onMousePressed(MouseEvent e) {
      if(gameStart) {
        myGW.onMousePressed(e);
      }
    }

    @Override
    protected void onMouseReleased(MouseEvent e) {
      if(gameStart) {
        myGW.onMouseReleased(e);
      }
    }

    @Override
    protected void onMouseDragged(MouseEvent e) {
      if(gameStart) {
        myGW.onMouseDragged(e);
      }
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
    //  if(gameStart) {
        myVP.setSize(newSize);
   //   } else {
        cutscene1.setSize(newSize);
        cutscene2.setSize(newSize);
   //   }
    }

  }

