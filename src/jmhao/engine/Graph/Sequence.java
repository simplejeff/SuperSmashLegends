package jmhao.engine.Graph;

import jmhao.engine.World.Status;

public class Sequence extends Composite{
  
  @Override
  public Status update(float seconds) {
    if(lastRunning == null) {
      for(BTNode n : children) {
        Status s = n.update(seconds);
        if(s == Status.FAILURE) {
          return Status.FAILURE;
        }
        if(s == Status.RUNNING) {
          lastRunning = n;
          return Status.RUNNING;
        }
      }
      return Status.SUCCESS;
    } else {
      return lastRunning.update(seconds);
    }
  }

  @Override
  public void reset() {
    lastRunning = null;
    for(BTNode n : children) {
      n.reset();
    }    
  }

}
