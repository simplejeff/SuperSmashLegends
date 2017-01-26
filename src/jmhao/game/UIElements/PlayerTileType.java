package jmhao.game.UIElements;

public enum PlayerTileType {
  NONE(0), PLAYER(1), ENEMY(2);

  public final int fId;

  private PlayerTileType(int id) {
      this.fId = id;
  }
  public int getTile() {
    return this.fId;
  }
}
