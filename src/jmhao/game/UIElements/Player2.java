package jmhao.game.UIElements;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import jmhao.engine.Collision.Collision;
import jmhao.engine.Collision.Raycast;
import jmhao.engine.Entity.Entity;
import jmhao.engine.Entity.EntityType;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Shape.Polygon;
import jmhao.engine.Shape.Shape;
import jmhao.engine.Shape.Rectangle;
import jmhao.game.Final;

public class Player2<T extends PhysicsEntity<T>> extends GamePhysicsEntity<T>{

  Rectangle r;
  boolean movingLeft = false;
  boolean movingRight = false;
  boolean player1;
  float maxMass = 20f;
  float percent = 0;
  double reload = 0;
  boolean frozen = false;
  boolean blinded = false;
  boolean knockedUp = false;
  boolean knockBack = false;
  boolean poisoned = false;
  //boolean jumping = false;
  boolean reloadPrimary = false;
  boolean reloadSecondary = false;
  boolean touchingGround = false;
  boolean facingLeft = true;
  boolean crouching = false;
  boolean standing = true;
  boolean walking = false;
  float debuffTimer = 0.0f;
  float primary = 0.0f;
  float secondary = 0.0f;
  float primaryReload = 1000f;
  float secondaryReload = 5000f;
  float jumpReload = 0f;
  int facing = 1;
  
  int attack = 0;
  int moving = 0;
  long totalMillis = 0;
  
  int character = 0;
  Shield<T> shield;
  Color original = Color.blue;
  boolean selected = false;
  int myHeight;
  int yVal;
  float gDir = -1;
  int jumpCount = 2;
  int width;
  final float gravity = .007f;
  private HashSet<Integer> keys = new HashSet<Integer>();
  GameWorld<T> myGW;
  public Player2(GameWorld<T> gw, Vec2f startLoc, float width, float length) {
    myGW = gw;
    Rectangle r = new Rectangle(new Vec2i((int)startLoc.x, (int)startLoc.y), 40, 80);
    setShape(r);
    setPos(startLoc);
    this.width = (int)width;
    myHeight = (int)length;
    //r = new Rectangle(startLoc, new Vec2f((float)(startLoc.x+width/2), (float)startLoc.y), new Vec2f(startLoc.x, (float)(startLoc.y+width)), new Vec2f((float)(startLoc.x+width), (float)(startLoc.y+width)));
    Vec2i shieldLoc = getShieldLoc();
    shield = new Shield<T>(gw, shieldLoc, (int)(width));
  }
  public void setChar(int character) {
    this.character = character;
    switch (character) {
      case 0:
        maxMass = 14f;
        break;
      case 1:
        maxMass = 16f;
      case 2:
        maxMass = 18f;
      case 3:
        maxMass = 20f;
    }
  }
  public float getPercent() {
    if(percent > 400) {
      return 400f;
    } else {
    return percent;
    }
  }
  public float getReload() {
    if(secondary < 0) {
      return 0;
    } else {
      return (secondary)/1000;
    }
  }
  @Override
  public boolean onCollide(Collision<T> c) {
    super.onCollide(c);
    /*GamePhysicsEntity<T> other = (GamePhysicsEntity<T>) c.getEntity();
    Vec2f revMtv = c.getMtv().smult(-1);
    other.collidePlayer(revMtv);*/
    return true;
  }
  @Override
  public boolean checkCrouching() {
    return crouching;
  }
  @Override
  public void negativeGravity() {
    g = new Vec2f(0f, -.003f);
    gDir = 1;
  }
  @Override
  public void positiveGravity() {
    g = new Vec2f(0f, .003f);
    gDir = -1;
  }
  @Override
  public void doCollide(T e) {
    ((GamePhysicsEntity<T>)e).collidePlayer2(this);
  }
  public void resetAllDebuffs() {
    frozen = false;
    blinded = false;
    knockedUp = false;
    knockBack = false;
    poisoned = false;
  }
  /*@Override
  public boolean checkCollision(Entity e) {
    shield.checkCollision(e);
    boolean b = s.collides(e.getShape());
    if(b) {
      switch(e.getEntity()) {
      case ENEMY:
        break;
      case BACKGROUNDVERT:
        touchingGround =
      b = (T)player2; true;
        Vec2i ground = e.getShape().getLocation();
        yVal = ground.y - myHeight;
        break;
      case BACKGROUNDHORIZ:
        bounceWall();
        break;
      }
    }
    return b;
  }*/
  public double getShield() {
    return shield.getShield();
  }
  @Override
  public void collideSpikes(Spikes<T> e) {
    System.out.println("collided spikes");
    myGW.loseGame();
  }
  @Override
  public void onTick(long millisSincePreviousTick) {
    totalMillis += millisSincePreviousTick;
    if(attack > 0 && totalMillis >= 500) {
      totalMillis = 0;
      attack = 0;
    }
    moving = ((int)(totalMillis/125))%4;
    if(percent > 400.0f) {
      percent = 400.0f;
    }
    mass = maxMass - maxMass*(percent/401.0f);
    mass = mass/3f;
    debuffTimer -= millisSincePreviousTick;
    primary -= millisSincePreviousTick;
    jumpReload -= millisSincePreviousTick;
    secondary -= millisSincePreviousTick;
    if(debuffTimer <= 0.0f) {
      resetAllDebuffs();
      debuffTimer = 0;
    }
    if(primary <= 0.0f) {
      primary = 0;
      reloadPrimary = false;
    }
    if(secondary <= 0.0f) {
      secondary = 0;
      reloadSecondary = false;
    }
    if(jumpReload <= 0.0f) {
      jumpReload = 0;
    }
    if(crouching) {
      setSize(new Vec2i(40, 60));
    } else {
      setSize(new Vec2i(40, 80));
    }
    if(touchingGround  && ((!keys.contains(KeyEvent.VK_LEFT) && !keys.contains(KeyEvent.VK_RIGHT)) || frozen)) {
      drag(millisSincePreviousTick);
    }
    shield.onTick(millisSincePreviousTick);
    Vec2i newShieldLoc = getShieldLoc();
    shield.changePos(newShieldLoc);
    primaryReload -= millisSincePreviousTick;
    myGW.updateReload((int)(primaryReload/10));
    if(knockedUp || knockBack || frozen) {
      crouching = false;
    } else {
      handleKeys();
    }
    super.onTick(millisSincePreviousTick);
    if(knockedUp && debuffTimer > 1400.0f) {
      vel = new Vec2f(0, vel.y);
    }
    /*if(!movingLeft && !movingRight) {
      drag(millisSincePreviousTick);
    }*/
   // vel = getVelocity();
    //s.setLocation(new Vec2i((int)pos.x, (int)pos.y));
  }
  @Override
  public void drawElement(Graphics2D g) {
   // super.drawElement(g);
    Vec2i initLoc = s.getCenter();
    Vec2i finalLoc = initLoc.plus(facing*width, 0);
    //g.drawLine(initLoc.x, initLoc.y, finalLoc.x, finalLoc.y);
    
    if(crouching) {
      switch (character) {
        case 0:
          addPos(new Vec2f(0, -8));
          Final.spriteTeemoCrouch.drawSprite(g, s.getLocation(), facing, 0);
          addPos(new Vec2f(0, 8));
          break;
        case 1:
          addPos(new Vec2f(-1, -8));
          Final.spriteEzrealCrouch.drawSprite(g, s.getLocation(), facing, 0);
          addPos(new Vec2f(1, 8));
          break;
        case 2:
          addPos(new Vec2f(-4, -14));
          Final.spriteYasuoCrouch.drawSprite(g, s.getLocation(), facing, 0);
          addPos(new Vec2f(4, 14));
          break;
        case 3:
          addPos(new Vec2f(0, -8));
          Final.spriteBraumCrouch.drawSprite(g, s.getLocation(), facing, 0);
          addPos(new Vec2f(0, 8));
          break;
        default:
          Final.spriteCrouch.drawSprite(g, s.getLocation(), facing, 0);
          break;
      }
     // Final.spriteCrouch.drawSprite(g, s.getLocation(), facing, 0);
    } else {
      if(attack > 0) {
        if(attack == 1) {
          switch (character) {
          case 0:
            addPos(new Vec2f(0, -8));
            Final.spriteTeemoShoot.drawSprite(g, s.getLocation(), facing, moving);
            addPos(new Vec2f(0, 8));
            break;
          case 1:
            addPos(new Vec2f(-6, -8));
            Final.spriteEzrealPunch.drawSprite(g, s.getLocation(), facing, moving);
            addPos(new Vec2f(6, 8));
            break;
          case 2:
            addPos(new Vec2f(-49, -8));
            Final.spriteYasuoPunch.drawSprite(g, s.getLocation(), facing, moving);
            addPos(new Vec2f(49, 8));
            break;
          case 3:
            addPos(new Vec2f(-10, 0));
            Final.spriteBraumPunch.drawSprite(g, s.getLocation(), facing, moving);
            addPos(new Vec2f(10, 0));
            break;
          default:
            Final.spriteShoot.drawSprite(g, s.getLocation(), facing, moving);
            break;
        }
          //Final.spriteShoot.drawSprite(g, s.getLocation(), facing, moving);
        }
        if(attack == 2) {
          switch (character) {
          case 0:
            addPos(new Vec2f(0, -8));
            Final.spriteTeemoShoot.drawSprite(g, s.getLocation(), facing, moving);
            addPos(new Vec2f(0, 8));
            break;
          case 1:
            addPos(new Vec2f(-6, -8));
            Final.spriteEzrealPunch.drawSprite(g, s.getLocation(), facing, moving);
            addPos(new Vec2f(6, 8));
            break;
          case 2:
            addPos(new Vec2f(-49, -8));
            Final.spriteYasuoPunch.drawSprite(g, s.getLocation(), facing, moving);
            addPos(new Vec2f(49, 8));
            break;
          case 3:
            addPos(new Vec2f(-10, 0));
            Final.spriteBraumPunch.drawSprite(g, s.getLocation(), facing, moving);
            addPos(new Vec2f(10, 0));
            break;
          default:
            Final.spriteShoot.drawSprite(g, s.getLocation(), facing, moving);
            break;
        }
          //Final.spritePunch.drawSprite(g, s.getLocation(), facing, moving);
        }
      } else {
        if(!keys.contains(KeyEvent.VK_LEFT) && !keys.contains(KeyEvent.VK_RIGHT)) {
          switch (character) {
          case 0:
            addPos(new Vec2f(0, -8));
            Final.spriteTeemoStand.drawSprite(g, s.getLocation(), facing, moving);
            addPos(new Vec2f(0, 8));
            break;
          case 1:
            addPos(new Vec2f(-2, -8));
            Final.spriteEzrealStand.drawSprite(g, s.getLocation(), facing, moving);
            addPos(new Vec2f(2, 8));
            break;
          case 2:
            addPos(new Vec2f(-10, -16));
            Final.spriteYasuoStand.drawSprite(g, s.getLocation(), facing, moving);
            addPos(new Vec2f(10, 16));
            break;
          case 3:
            Final.spriteBraumStand.drawSprite(g, s.getLocation(), facing, moving);
            break;
          default:
            Final.spriteStand.drawSprite(g, s.getLocation(), facing, moving);
            break;
        }
          //Final.spriteStand.drawSprite(g, s.getLocation(), facing, moving);
        } else {
          if(touchingGround && !frozen) {
            switch (character) {
            case 0:
              addPos(new Vec2f(0, -8));
              Final.spriteTeemoWalk.drawSprite(g, s.getLocation(), facing, moving);
              addPos(new Vec2f(0, 8));
              break;
            case 1:
              addPos(new Vec2f(0, -8));
              Final.spriteEzrealWalk.drawSprite(g, s.getLocation(), facing, moving);
              addPos(new Vec2f(0, 8));
              break;
            case 2:
              addPos(new Vec2f(-10, -16));
              Final.spriteYasuoWalk.drawSprite(g, s.getLocation(), facing, moving);
              addPos(new Vec2f(10, 16));
              break;
            case 3:
              addPos(new Vec2f(-2, 0));
              Final.spriteBraumWalk.drawSprite(g, s.getLocation(), facing, moving);
              addPos(new Vec2f(2, 0));
              break;
            default:
              Final.spriteWalk.drawSprite(g, s.getLocation(), facing, moving);
              break;
          }
            //Final.spriteWalk.drawSprite(g, s.getLocation(), facing, moving);
          } else {
            switch (character) {
            case 0:
              addPos(new Vec2f(0, -8));
              Final.spriteTeemoStand.drawSprite(g, s.getLocation(), facing, moving);
              addPos(new Vec2f(0, 8));
              break;
            case 1:
              addPos(new Vec2f(-2, -8));
              Final.spriteEzrealStand.drawSprite(g, s.getLocation(), facing, moving);
              addPos(new Vec2f(2, 8));
              break;
            case 2:
              addPos(new Vec2f(-10, -16));
              Final.spriteYasuoStand.drawSprite(g, s.getLocation(), facing, moving);
              addPos(new Vec2f(10, 16));
              break;
            case 3:
              Final.spriteBraumStand.drawSprite(g, s.getLocation(), facing, moving);
              break;
            default:
              Final.spriteStand.drawSprite(g, s.getLocation(), facing, moving);
              break;
            }
          }
        }
      }
    }
    Vec2i effectLoc = new Vec2i(s.getLocation().x - 16, s.getLocation().y - 50);
    if(frozen) {
      Final.spriteEffects.drawSprite(g, effectLoc, facing, moving);
    }else if(knockedUp) {
      Final.spriteEffects.drawSprite(g, effectLoc, facing, 4+moving);
    }else if(poisoned) {
      Final.spriteEffects.drawSprite(g, effectLoc, facing, 8+moving);
    }
  }
  public void handleKeys() {
    if(keys.contains(KeyEvent.VK_DOWN)) {
      if(touchingGround) {
        attack = 0;
        crouching = true;
      }
    } else {
      crouching = false;
      if(keys.contains(KeyEvent.VK_UP)) {
        if(jumpCount > 0 && jumpReload < 0.1f) {
          touchingGround = false;
          jumpCount --;
          jumpReload = 200f;
          vel = new Vec2f(vel.x, -15f/maxMass);
        //  this.setPos(new Vec2f(pos.x, pos.y-10));
          updateImpulse(new Vec2f(0, -impulse.y));
        }
      }
      if(keys.contains(KeyEvent.VK_LEFT)) {
        if(facing != -1) {
          if(attack == 0) {
            totalMillis = 0;
          }
        }
        facing = -1;
        if(vel.x > -.4f) {
          vel = vel.plus(new Vec2f(-.5f/maxMass, 0));
        }
        if(touchingGround) {
          vel = new Vec2f(-.3f, vel.y);
        }
      }
      if(keys.contains(KeyEvent.VK_RIGHT)) {
        if(facing != 1) {
          if(attack == 0) {
            totalMillis = 0;
          }
        }
        facing = 1;
        if(vel.x < .4f) {
          vel = vel.plus(new Vec2f(.5f/maxMass, 0));
        }
        if(touchingGround) {
          vel = new Vec2f(.3f, vel.y);
        }
      }
      if(keys.contains(KeyEvent.VK_NUMPAD1)) {
        if(!poisoned && !reloadPrimary && !crouching) {
            Vec2f bulletDir = new Vec2f(facing, 0);
            if(character < 2) {
              totalMillis = 0;
              attack = 1;
              myGW.addProjectileTwo((T)new Bullet<T>(myGW, s.getCenter(), bulletDir, false, facing, character));
            } else {
              totalMillis = 0;
              attack = 2;
              myGW.addProjectileTwo((T)new Punch<T>(myGW, s.getCenter(), bulletDir, false, facing));
            }
            primary = 1000.0f;
            reloadPrimary = true;
        }
      }
      if(keys.contains(KeyEvent.VK_NUMPAD2)) {
        if(!poisoned && !reloadSecondary && !crouching) {
            Vec2f bulletDir = new Vec2f(facing, 0);
            if(character == 0) {
              totalMillis = 0;
              attack = 1;
              myGW.addProjectileTwo((T)new Poison<T>(myGW, s.getCenter(), bulletDir, true, facing));
            } else if (character == 1) {
              totalMillis = 0;
              attack = 1;
              T blink = (T)new Blink<T>(s.getLocation());
              myGW.addEntity(blink);
              myGW.addNoCollision(blink);
              setPos(new Vec2f(getPos().x + facing*200, getPos().y));
             //myGW.addProjectileOne((T)new Blink<T>(myGW, s.getCenter(), bulletDir, true));
            } else if (character == 2) {
              totalMillis = 0;
              attack = 2;
              myGW.addProjectileTwo((T)new Tornado<T>(myGW, new Vec2i(s.getLocation().x-40, s.getLocation().y-80), bulletDir, true));
            } else {
              totalMillis = 0;
              attack = 2;
              myGW.addProjectileTwo((T)new Freeze<T>(myGW, s.getCenter(), bulletDir, true, facing));
            }
            secondary = 5000.0f;
            reloadSecondary = true;
        }
      }
    }
  }
  @SuppressWarnings("unchecked")
  @Override
  public void onKeyPressed(KeyEvent e) {
   // shield.onKeyPressed(e);
    int keyCode = e.getKeyCode();
    if(!keys.contains(keyCode)) {
      if(keyCode == KeyEvent.VK_LEFT) {
        keys.remove(KeyEvent.VK_RIGHT);
        keys.remove(KeyEvent.VK_DOWN);
      }
      if(keyCode == KeyEvent.VK_RIGHT) {
        keys.remove(KeyEvent.VK_LEFT);
        keys.remove(KeyEvent.VK_DOWN);
      }
      if(keyCode == KeyEvent.VK_UP) {
        keys.remove(KeyEvent.VK_DOWN);
      }
      keys.add(keyCode);
    }
  }
  @Override
  public void onKeyReleased(KeyEvent e) {
    shield.onKeyReleased(e);
    int keyCode = e.getKeyCode();
    keys.remove(keyCode);
    switch(keyCode) {
    case KeyEvent.VK_UP:
      break;
    case KeyEvent.VK_LEFT:
     // movingLeft = false;
      break;
    case KeyEvent.VK_DOWN:
      break;
    case KeyEvent.VK_RIGHT:
     // movingRight = false;
      break;
    default:
      break;
    }
  }

  @Override
  public void setSize(Vec2i newSize) {
    Rectangle r = (Rectangle)getShape();
    Vec2i playerLoc = r.getLocation();
    int heightChange = (int)r.getLength() - newSize.y;
    int bottomY = playerLoc.y + heightChange;
    Rectangle newR = new Rectangle(new Vec2i(playerLoc.x, bottomY), newSize.x, newSize.y);
    setShape(newR);
    setPos(new Vec2f(playerLoc.x, bottomY));
    /*Vec2i playerLoc = getShape().getLocation();
    int leftLimit = playerLoc.x;
    double percentWidth = (double)leftLimit/screenSize.x;
    screenSize = newSize;
    int xChange = (int)(percentWidth*screenSize.x);
    Vec2f newPlayerLoc = new Vec2f(xChange, playerLoc.y);
    setPos(newPlayerLoc);*/
  }

  @Override
  public boolean isSelected(Point p) {
    return s.isSelected(p);
  }

  @Override
  public Shape getShape() {
    return s;
    
  }
  @Override
  public void setSelected(boolean s) {
    selected = s;
  }
  @Override
  public void changeColor(Color newC) {
    s.setColor(newC);
  }
  @Override
  public EntityType getEntity() {
    return EntityType.PLAYER;
  }
  @Override
  public void changePos(Vec2i change) {
    Vec2i corner = s.getLocation();
    corner = corner.minus(change);
    s.setLocation(corner);
  }
  public Vec2i getShieldLoc() {
    Vec2i corner = s.getLocation();
    corner = corner.minus(new Vec2i(myHeight/2, myHeight/2));
    return corner;
  }
  @Override
  public void reset() {
    super.reset();
  }
  @Override
  public void collidePlayer(Player<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collideVertWall(BackgroundVert<T> e) {
    touchingGround = true;
    jumpCount = 2;
   // reset all velocity, reset all impulse
  }
  @SuppressWarnings("unchecked")
  @Override
  public void collideBullet(Bullet<T> e) {
    myGW.addNumParticles(new Vec2f(s.getCenter().x, s.getCenter().y), 100, 400.0f, Color.red);
    //set small impulse up
   // resetAllDebuffs(); 
    knockBack = true;
    if(debuffTimer < 150.0f) {
      debuffTimer = 150.0f;
      if(crouching) {
        debuffTimer = 100.0f;
      }
    }
    percent += 10.0f;
    if(crouching) {
      this.updateImpulse(new Vec2f(0, -2.5f));
    } else {
      this.updateImpulse(new Vec2f(0, -4f));
    }
    touchingGround = false;
  }
  @Override
  public void collidePunch(Punch<T> e) {
    myGW.addNumParticles(new Vec2f(s.getCenter().x, s.getCenter().y), 150, 400.0f, Color.red);
    //set small medium impulse up
    //resetAllDebuffs();
    knockBack = true;
    if(debuffTimer < 150.0f) {
      debuffTimer = 200.0f;
      if(crouching) {
        debuffTimer = 150.0f;
      }
    }
    percent += 15.0f;
    if(crouching) {
      this.updateImpulse(new Vec2f(0, -2.5f));
    } else {
      this.updateImpulse(new Vec2f(0, -4f));
    }
    touchingGround = false;
  }
  @Override
  public void collideFreeze(Freeze<T> e) {
    myGW.addNumParticles(new Vec2f(s.getCenter().x, s.getCenter().y), 200, 400.0f, Color.blue);
    resetAllDebuffs();
    frozen = true;
    debuffTimer = 2000.0f;
    percent += 20.0f;
    vel = new Vec2f(0, 0);
    //vel = new Vec2f(0, 0);
    nullForces();
    
  }
  @Override
  public void collideTornado(Tornado<T> e) {
    /*if(player1) {
      myGW.removeProjectileTwo(e);
    } else {
      myGW.removeProjectileOne(e);
    }*/
    myGW.addNumParticles(new Vec2f(s.getCenter().x, s.getCenter().y), 400, 400.0f, Color.gray);
    resetAllDebuffs();
    knockedUp = true;
    debuffTimer = 1500.0f;
    if(crouching) {
      debuffTimer = 1000.0f;
    }
    percent += 20.0f;
    vel = new Vec2f(0, 0);
    if(crouching) {
      this.updateImpulse(new Vec2f(0, -6));
    } else {
      this.updateImpulse(new Vec2f(0, -12));
    }
    touchingGround = false;
    
    //apply vertical impulse like jump, reset velocity to 0
  }
  @Override
  public void collidePoison(Poison<T> e) {
   /* if(player1) {
      myGW.removeProjectileTwo(e);
    } else {
      myGW.removeProjectileOne(e);
    }*/
    myGW.addNumParticles(new Vec2f(s.getCenter().x, s.getCenter().y), 150, 400.0f, Color.magenta);
    //set small impulse up
    resetAllDebuffs();
    poisoned = true;
    percent += 15.0f;
    debuffTimer = 2000.0f;
    if(crouching) {
      debuffTimer = 1500.0f;
    }
    percent += 20.0f;
    vel = new Vec2f(0, 0);
    if(crouching) {
      this.updateImpulse(new Vec2f(0, -2));
    } else {
      this.updateImpulse(new Vec2f(0, -3));
    }
    touchingGround = false;
  }
  @Override
  public void collideHorizWall(BackgroundHoriz<T> e) {
 //   System.out.println("we hit it!");
    setPos(new Vec2f(getPos().x, getPos().y+2));
  }
  @Override
  public void collideBoss(Boss<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collideGrenade(Grenade<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collideEnemy(Enemy<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collideEnemy2(Enemy2<T> e) {
    // TODO Auto-generated method stub
    
  }
  @Override
  public void collidePlayer2(Player2<T> e) {
    // TODO Auto-generated method stub
    
  }
}

