package jmhao.engine.Graph;

import jmhao.engine.World.Status;

public class Selector extends Composite{

  @Override
  public Status update(float seconds) {
    for(BTNode n : children) {
      Status s = n.update(seconds);
      if(s == Status.SUCCESS) {
        return Status.SUCCESS;
      }
      if(s == Status.RUNNING) {
        if(lastRunning != n) {
          reset();
        }
        lastRunning = n;
        return Status.RUNNING;
      }
    }
    return Status.FAILURE;
  }

  @Override
  public void reset() {
    lastRunning = null;
    for(BTNode n : children) {
      n.reset();
    }    
  }

}
