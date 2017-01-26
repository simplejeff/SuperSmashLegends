package jmhao.engine.Graph;

import jmhao.engine.World.Status;

public interface BTNode {
  Status update(float seconds);
  void reset();
}
