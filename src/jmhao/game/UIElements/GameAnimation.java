package jmhao.game.UIElements;

import jmhao.engine.Drawing.Animation;
import cs195n.Vec2f;

public class GameAnimation implements Animation{
  private Vec2f myDir;
  private Vec2f myPos;
  public GameAnimation(Vec2f pos, Vec2f dir) {
    myPos = pos;
    myDir = dir;
  }

  @Override
  public Vec2f getPos() {
    return myPos;
  }

  @Override
  public Vec2f getDir() {
    return myDir;
  }

}
