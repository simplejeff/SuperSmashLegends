package jmhao.game.UIElements;

import cs195n.Vec2f;
import jmhao.engine.Entity.Entity;
import jmhao.engine.Entity.PhysicsEntity;

public abstract class GamePhysicsEntity<T extends PhysicsEntity<T>> extends PhysicsEntity<T> {

  public abstract void collidePlayer(Player<T> e);
  public abstract void collideVertWall(BackgroundVert<T> e);
  public abstract void collideHorizWall(BackgroundHoriz<T> e);
  public abstract void collideBoss(Boss<T> e);
  public abstract void collideGrenade(Grenade<T> e);
  public abstract void collideEnemy(Enemy<T> e);
  public abstract void collideEnemy2(Enemy2<T> e);
  public abstract void collidePlayer2(Player2<T> e);
  public void collideSpikes(Spikes<T> e) {}
  public void collideBullet(Bullet<T> e) {}
  public void collidePunch(Punch<T> e) {}
  public void collideFreeze(Freeze<T> e) {}
  public void collideTornado(Tornado<T> e) {}
  public void collidePoison(Poison<T> p) {}
}
