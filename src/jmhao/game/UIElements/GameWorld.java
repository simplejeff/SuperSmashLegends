package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import jmhao.engine.Collision.Collision;
import jmhao.engine.Entity.Entity;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Particle.Particle;
import jmhao.engine.Viewport.Viewportable;
import jmhao.engine.World.PhysicsWorld;
import jmhao.engine.World.World;
import jmhao.game.Final;

public class GameWorld<T extends PhysicsEntity<T>> extends PhysicsWorld<T>implements Viewportable{
  Vec2i currScreenSize;
  int total = 1000;
  int currParticle = 0;
  Random r = new Random();
  boolean filled = false;
  ArrayList<Particle> particles = new ArrayList<Particle>();
  private HashMap<String, T> gameEntities = new HashMap<String, T>();
  //private HashMap<String, T> engineEntities = new HashMap<String, T>();
  private ArrayList<T> engineEntities = new ArrayList<T>();
  private ArrayList<T> noCollision = new ArrayList<T>();
  private ArrayList<T> breakable = new ArrayList<T>();
  private ArrayList<T> projectileOne = new ArrayList<T>();
  private ArrayList<T> projectileTwo = new ArrayList<T>();
  private ArrayList<T> killBox = new ArrayList<T>();
  HealthMessage hp;
  HealthMessage hp2;
  HealthMessage bossHp;
  ReloadMessage reload;
  ReloadMessage reload2;
  ReloadMessage shield;
  ReloadMessage round;
  Player<T> player;
  Player2<T> player2;
  Integer hpVal = 100;
  Integer bossHpVal = 100;
  int roundVal = 0;
  long respawn = 1000;
  boolean gameOver = false;
  boolean gameWon = false;
  int spawnLeft;
  int spawnRight;
  int char1;
  int char2;
  Integer reloadVal = 0;
  Integer shieldVal = 0;
  BackgroundVert<T> bg1, bg5;
  BackgroundHoriz<T> bg2, bg3, bg4;
  ArrayList<Grenade<T>> grenades = new ArrayList<Grenade<T>>();
  ArrayList<Ray<T>> rays = new ArrayList<Ray<T>>();
  @SuppressWarnings("unchecked")
  public GameWorld(Vec2i screenSize) {
    currScreenSize = screenSize;
    /*TestCircle cOne = new TestCircle(new Vec2i(0, 0), 25);
    TestCircle cTwo = new TestCircle(new Vec2i(200, 200), 25);
    TestRect rOne = new TestRect(new Vec2i(200, 100), 50, 50);
    TestRect rTwo = new TestRect(new Vec2i(100, 200), 50, 50);
    addEntity(cOne);
    addEntity(cTwo);
    addEntity(rOne);
    addEntity(rTwo);*/
     hp = new HealthMessage(new Vec2f(50f, 50f), "0%");
     hp2 = new HealthMessage(new Vec2f(650f, 50f), "0%");
  //  bossHp = new HealthMessage(new Vec2f(650f, 50f), "Boss:100");
    reload = new ReloadMessage(new Vec2f(200f, 50f), "Reload H: 0");
    reload2 = new ReloadMessage(new Vec2f(800f, 50f), "Reload 2: 0");
   /* shield = new ReloadMessage(new Vec2f(450f, 50f), "shield: 0");
    player = new Player<T>(this, new Vec2i(500, 100), 30, 30);
    player.setMass(10f);
    round = new ReloadMessage(new Vec2f(350f, 50f), "Round: 0");
    spawnLeft = 200;
    spawnRight = 800;
    bg1 = new Backs.getLocation().xgroundVert<T>(new Vec2i(60, 320), 920, 80);
    bg5 = new BackgroundVert<T>(new Vec2i(60, 300), 550, 80);
    bg1.setRest(0f);
    bg5.setRest(1f);
    bg2 = new BackgroundHoriz<T>(new Vec2i(60, 0), 80, 400 );
    bg3 = new BackgroundHoriz<T>(new Vec2i(900, 0), 80, 400 );
    bg4 = new BackgroundHoriz<T>(new Vec2i(60, 0), 920, 80);
    bg2.setRest(.5f);
    bg3.setRest(.5f);
    bg4.setRest(.5f);
    addEntity((T)player);
    addEntity((T)bg1);
    addEntity((T)bg2);
    addEntity((T)bg3);
    addEntity((T)bg4);
    addEntity((T)bg5);
    Enemy<T> e = new Enemy<T>(this, new Vec2i((int)(200), 160), 20, -.3f);
    e.setMass(5f);
    e.setRest(1f);
    addEntity((T)e);
    boss = new Boss<T>(this, new Vec2i((int)(700), 160), 50);
    boss.setMass(5f);
    addEntity((T)boss);*/
  }
  public void setChar1(int character) {
    player.setChar(character);
    player.setSize(new Vec2i(20, 40));
    char1 = character;
  }
  public void setChar2(int character) {
    player2.setChar(character);
    player2.setSize(new Vec2i(20, 40));
    char2 = character;
  }
  
  public Particle generateParticle(Vec2f location, float maxLife, Color c) {
    float lifespan = (float)r.nextDouble()*(maxLife-50.0f) + 50.0f;
    Vec2f velocity = new Vec2f((float)r.nextDouble()*2.0f - 1.0f, (float)r.nextDouble()*3.0f - 2.0f);
    return new Particle(location, velocity, lifespan, maxLife, c);
  }
  
  public void addParticle(Vec2f location, float maxLife, Color c) {
    Particle p = generateParticle(location, maxLife, c);
    if(!filled) {
      particles.add(p);
      filled = (particles.size() >= total);
      if(filled) {
        currParticle = 0;
        particles.set(currParticle, p);
      }
    } else {
      particles.set(currParticle, p);
      currParticle++;
      currParticle %= total;
    }
  }

  public void addNumParticles(Vec2f location, int num, float maxLife, Color c) {
    for(int i= 0; i< num; i++) {
      addParticle(location, maxLife, c);
    }
  }
  
  public void gravityUp() {
    for(T e : entityList) {
      e.positiveGravity();
    }
  }
  public void gravityDown() {
    for(T e : entityList) {
      e.negativeGravity();
    }
  }
  public Vec2f playerPos() {
    return player.getPos();
  }
  public boolean checkBreakable(T entity) {
    return breakable.contains(entity);
  }
  public void setPlayer(Player<T> p) {
    player = p;
  }
  public void setPlayer2(Player2<T> p) {
    player2 = p;
  }
  public void addGrenade(Grenade<T> g) {
    grenades.add(g);
  }
  public void removeGrenade(Grenade<T> g) {
    grenades.remove(g);
  }
  public void addKillbox(T t) {
    killBox.add(t);
  }
  public void addProjectileOne(T t) {
    projectileOne.add(t);
  }
  public void addProjectileTwo(T t) {
    projectileTwo.add(t);
  }
  public void removeProjectileOne(T t) {
    projectileOne.remove(t);
  }
  public void removeProjectileTwo(T t) {
    projectileTwo.remove(t);
  }
  public void addRay(Ray<T> r) {
    rays.add(r);
  }
  public void removeRay(Ray<T> r) {
    rays.remove(r);
  }
  @SuppressWarnings("unchecked")
  public void spawnEnemies() {
   /* if(roundVal < 2) {
      Enemy<T> e = new Enemy<T>(this, new Vec2i((int)(200), 160), 20, -.3f);
      e.setMass(5);
      Enemy2<T> e2 = new Enemy2<T>(this, new Vec2i((int)(800), 160), 20, .3f);
      e2.setMass(5);
      addEntity((T)e);
      addEntity((T)e2);
      roundVal++;
      round.setMessage("Round: "+roundVal);
    } else if(roundVal == 2) {
      boss = new Boss<T>(this, new Vec2i((int)(700), 160), 50);
      boss.setMass(5);
      addEntity((T)boss);
      round.setMessage("Boss");
      roundVal++;
    } else {}*/
  }
  public void addEngineEntity(T e) {
    engineEntities.add(e);
  }
  public void addBreakable(T e) {
    breakable.add(e);
  }
  public void addNoCollision(T e) {
    noCollision.add(e);
  }
  @Override
  public void getTick(long millisSincePrevTick) {
    player.reset();
    player2.reset();
    for(Iterator<T> it = noCollision.iterator(); it.hasNext();) {
      T e = it.next();
      if(e.remove()) {
        removeEntity(e);
        it.remove();
        continue;
      }
    }
    T a, b;
    Vec2f playerMtv = player.checkCollision((T)player2);
    if(playerMtv != null && !playerMtv.isZero()) {
      a = (T)player;
      b = (T)player2;
      Vec2f otherMtv = playerMtv.smult(-1);
      float myVel = project(a.getVelocity(), playerMtv);
      float otherVel = project(b.getVelocity(), playerMtv);
     /* myVel = (unit(otherMtv)).smult(dist(a.getVelocity()));
      otherVel = (unit(myMtv)).smult(dist(b.getVelocity()));*/
      Collision<T> thisC = new Collision<T>(b, playerMtv, playerMtv, myVel, otherVel, a.getShape(), b.getShape());
      Collision<T> otherC = new Collision<T>(a, playerMtv, otherMtv, otherVel, myVel, b.getShape(), a.getShape());
          a.onCollide(thisC);
          b.onCollide(otherC);
    }
    Vec2f myMtv, otherMtv;
    float myVel, otherVel;
    Collision<T> otherC, thisC;
    for(T e : killBox) {
      myMtv = player.checkCollision(e);
      if(myMtv != null && !myMtv.isZero()) {
        loseGame();
      }
      myMtv = player2.checkCollision(e);
      if(myMtv != null && !myMtv.isZero()) {
        loseGame();
      }
    }
    for(T e : entityList) {
      e.onTick(millisSincePrevTick);
      myMtv = player.checkCollision(e);
      if(myMtv != null && !myMtv.isZero()) {
        a = (T)player;
        b = e;
        otherMtv = myMtv.smult(-1);
        myVel = project(a.getVelocity(), myMtv);
        otherVel = project(b.getVelocity(), myMtv);
       /* myVel = (unit(otherMtv)).smult(dist(a.getVelocity()));
        otherVel = (unit(myMtv)).smult(dist(b.getVelocity()));*/
        thisC = new Collision<T>(b, myMtv, myMtv, myVel, otherVel, a.getShape(), b.getShape());
        otherC = new Collision<T>(a, myMtv, otherMtv, otherVel, myVel, b.getShape(), a.getShape());
        boolean aCollide = !(b.isGrenade());
        boolean bCollide = !(a.isGrenade());
        if(!noCollision.contains(b)) {
          if(aCollide) {
            a.onCollide(thisC);
          }
          if(bCollide) {
            //b.onCollide(otherC);
          }
        }
        b.doCollide(a);
        //a.doCollide(b);
      }
      
      myMtv = player2.checkCollision(e);
      if(myMtv != null && !myMtv.isZero()) {
        a = (T)player2;
        b = e;
        otherMtv = myMtv.smult(-1);
        myVel = project(a.getVelocity(), myMtv);
        otherVel = project(b.getVelocity(), myMtv);
       /* myVel = (unit(otherMtv)).smult(dist(a.getVelocity()));
        otherVel = (unit(myMtv)).smult(dist(b.getVelocity()));*/
        thisC = new Collision<T>(b, myMtv, myMtv, myVel, otherVel, a.getShape(), b.getShape());
        otherC = new Collision<T>(a, myMtv, otherMtv, otherVel, myVel, b.getShape(), a.getShape());
        boolean aCollide = !(b.isGrenade());
        boolean bCollide = !(a.isGrenade());
        if(!noCollision.contains(b)) {
          if(aCollide) {
            a.onCollide(thisC);
          }
          if(bCollide) {
            //b.onCollide(otherC);
          }
        }
        b.doCollide(a);
        //a.doCollide(b);
      }
    }
    for(Iterator<T> it = projectileOne.iterator(); it.hasNext();) {
      T e = it.next();
      GamePhysicsEntity<T> checkRemove = (GamePhysicsEntity<T>)e;
      if(checkRemove.remove()) {
        it.remove();
        continue;
      } else {
        e.onTick(millisSincePrevTick);
        myMtv = player2.checkCollision(e);
        if(myMtv != null && !myMtv.isZero()) {
          a = (T)player2;
          b = e;
          otherMtv = myMtv.smult(-1);
          myVel = project(a.getVelocity(), myMtv);
          otherVel = project(b.getVelocity(), myMtv);
          if(player2.checkCrouching()) {
            Vec2f changeVel = b.getVelocity();
            changeVel = changeVel.smult(.5f);
            otherVel = project(changeVel, myMtv);
          }
          /* myVel = (unit(otherMtv)).smult(dist(a.getVelocity()));
          otherVel = (unit(myMtv)).smult(dist(b.getVelocity()));*/
          thisC = new Collision<T>(b, myMtv, myMtv, myVel, otherVel, a.getShape(), b.getShape());
          a.onCollide(thisC);
          b.doCollide(a);
          it.remove();
          //a.doCollide(b);
        } else {
          for(T e2 : killBox) {
            myMtv = e2.checkCollision(e);
            if(myMtv != null && !myMtv.isZero()) {
              it.remove();
              break;
            }
          }
        }
      }
    }
    for(Iterator<T> it = projectileTwo.iterator(); it.hasNext();) {
      T e = it.next();
      GamePhysicsEntity<T> checkRemove = (GamePhysicsEntity<T>)e;
      if(checkRemove.remove()) {
        it.remove();
        continue;
      } else {
        e.onTick(millisSincePrevTick);
        myMtv = player.checkCollision(e);
        if(myMtv != null && !myMtv.isZero()) {
          a = (T)player;
          b = e;
          otherMtv = myMtv.smult(-1);
          myVel = project(a.getVelocity(), myMtv);
          otherVel = project(b.getVelocity(), myMtv);
          if(player.checkCrouching()) {
            Vec2f changeVel = b.getVelocity();
            changeVel = changeVel.smult(.5f);
            otherVel = project(changeVel, myMtv);
          }
         /* myVel = (unit(otherMtv)).smult(dist(a.getVelocity()));
          otherVel = (unit(myMtv)).smult(dist(b.getVelocity()));*/
          thisC = new Collision<T>(b, myMtv, myMtv, myVel, otherVel, a.getShape(), b.getShape());
          a.onCollide(thisC);
          b.doCollide(a);
          it.remove();
          //a.doCollide(b);
        } else {
          for(T e2 : killBox) {
            myMtv = e2.checkCollision(e);
            if(myMtv != null && !myMtv.isZero()) {
              it.remove();
              break;
            }
          }
        }
      }
    }
    for(T e : engineEntities) {
      e.onTick(millisSincePrevTick);
      myMtv = player.checkCollision(e);
      if(myMtv != null && !myMtv.isZero()) {
        a = (T)player;
        b = e;
        otherMtv = myMtv.smult(-1);
        myVel = project(a.getVelocity(), myMtv);
        otherVel = project(b.getVelocity(), myMtv);
       /* myVel = (unit(otherMtv)).smult(dist(a.getVelocity()));
        otherVel = (unit(myMtv)).smult(dist(b.getVelocity()));*/
        thisC = new Collision<T>(b, myMtv, myMtv, myVel, otherVel, a.getShape(), b.getShape());
        otherC = new Collision<T>(a, myMtv, otherMtv, otherVel, myVel, b.getShape(), a.getShape());
        boolean aCollide = !(b.isGrenade());
        boolean bCollide = !(a.isGrenade());
        if(!noCollision.contains(b)) {
          if(aCollide) {
            a.onCollide(thisC);
          }
          if(bCollide) {
            //b.onCollide(otherC);
          }
        }
        b.doCollide(a);
        //a.doCollide(b);
      }
      
      myMtv = player2.checkCollision(e);
      if(myMtv != null && !myMtv.isZero()) {
        a = (T)player2;
        b = e;
        otherMtv = myMtv.smult(-1);
        myVel = project(a.getVelocity(), myMtv);
        otherVel = project(b.getVelocity(), myMtv);
       /* myVel = (unit(otherMtv)).smult(dist(a.getVelocity()));
        otherVel = (unit(myMtv)).smult(dist(b.getVelocity()));*/
        thisC = new Collision<T>(b, myMtv, myMtv, myVel, otherVel, a.getShape(), b.getShape());
        otherC = new Collision<T>(a, myMtv, otherMtv, otherVel, myVel, b.getShape(), a.getShape());
        boolean aCollide = !(b.isGrenade());
        boolean bCollide = !(a.isGrenade());
        if(!noCollision.contains(b)) {
          if(aCollide) {
            a.onCollide(thisC);
          }
          if(bCollide) {
            //b.onCollide(otherC);
          }
        }
        b.doCollide(a);
        //a.doCollide(b);
      }
    }
    for(int i = 0; i < rays.size(); i++) {
      rays.get(i).onTick(millisSincePrevTick);
    }
//    for(Ray r : rays) {
//      r.onTick(millisSincePrevTick);
//    }
  /*  respawn -= millisSincePrevTick;
    if(respawn < 0) {
      respawn = 5000;
      spawnEnemies();
    }*/
    player.onTick(millisSincePrevTick);
    player2.onTick(millisSincePrevTick);
  }
  @Override
  public void onKeyPressed(KeyEvent e) {
    player.onKeyPressed(e);
    player2.onKeyPressed(e);
  }
  public void removeEntity(Entity e) {
    entityList.remove(e);
  }
  @Override
  public void drawElement(Graphics2D g) {
    g.drawImage(Final.bgPng, -50, 0, 1250, 802, 0, 0, 2000, 1000, null);
    
    /*for(T e : nonCollide) {
      e.drawElement(g);
    }*/
    for(Entity et : entityList) {
      et.drawElement(g);
    }
    for(Entity et : engineEntities) {
      et.drawElement(g);
    }
    for(Ray<T> r : rays) {
      r.drawElement(g);
    }
    for(Iterator<Particle> it = particles.iterator(); it.hasNext();) {
      Particle p = it.next();
      if(p.draw(g)) {
        it.remove();
        filled = false;
      }
    }
    float alpha = 1.0f;
    AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    g.setComposite(alcom);
    g.setColor(Color.black);
    for(Entity et : projectileOne) {
      et.drawElement(g);
    }
    for(Entity et : projectileTwo) {
      et.drawElement(g);
    }
    hp.setHpMessage(player.getPercent());
    hp2.setHpMessage(player2.getPercent());
    reload.setMessage("Reload H: " + player.getReload());
    reload2.setMessage("Reload 2: " + player2.getReload());
    hp.drawElement(g);
    hp2.drawElement(g);
    reload.drawElement(g);
    reload2.drawElement(g);
    player.drawElement(g);
    player2.drawElement(g);
  /*  hp.drawElement(g);
    bossHp.drawElement(g);
    reload.drawElement(g);
    shield.drawElement(g);*/
  }

  @Override
  public void onMouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onMousePressed(MouseEvent e) {

  }

  @Override
  public void onMouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onMouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void onMouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onResize(Vec2i newSize) {
    currScreenSize = newSize;
    /*for(Entity et : entityList) {
      et.setSize(newSize);
    }*/
  }
  @Override
  public void onKeyReleased(KeyEvent e) {
    player.onKeyReleased(e);
    player2.onKeyReleased(e);
  }
  public void loseGame() {
    gameOver = true;
  }
  public void winGame() {
    gameWon = true;
  }
  public void lowerHp() {
    hpVal -= 10;
    if(hpVal <= 0) {
      gameOver = true;
    }
    hp.setMessage(hpVal.toString());
  }
  public void lowerBossHp() {
    bossHpVal -= 10;
    if(bossHpVal <= 0) {
      gameWon = true;
    }
    bossHp.setMessage("Boss:" + bossHpVal.toString());
  }
  public boolean gameWon() {
    return gameWon;
  }
  public boolean gameOver() {
    return gameOver;
  }
  public void updateReload(int i) {
    reloadVal = i;
    if(reloadVal == 0) {
      reload.setMessage("Ready to shoot");
    } else {
      reload.setMessage("Reload time : " + reloadVal.toString());
    }
  }
  public void updateShield(int i) {
   /* shieldVal = i;
    if(shieldVal == 0) {
      shield.setMessage("Ready to shield");
    } else {
      shield.setMessage("Shield time : " + shieldVal.toString());
    }*/
  }
  @Override
  public int getWidth() {
    return 1200;
  }
  @Override
  public int getLength() {
    return 330;
  }
  @Override
  public void setSelected(Point p) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub
    
  }

}
