package jmhao.engine.Entity;

public enum EntityType {
  BULLET(0), BACKGROUNDHORIZ(1), BACKGROUNDVERT(2), PLAYER(3), ENEMY(4), ENEMY2(5), SHIELD(6);

  public final int fId;

  private EntityType(int id) {
      this.fId = id;
  }
  public int getEntity() {
    return this.fId;
  }
}