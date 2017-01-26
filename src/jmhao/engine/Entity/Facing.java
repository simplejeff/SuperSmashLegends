package jmhao.engine.Entity;

public enum Facing {
  UP(1), RIGHT(2), DOWN(3), LEFT(4);

  public final int fId;

  private Facing(int id) {
      this.fId = id;
  }
  public int getFace() {
    return this.fId;
  }
  
}
