package jmhao.engine.Graph;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Composite implements BTNode {
  protected ArrayList<BTNode> children = new ArrayList<BTNode>();
  protected BTNode lastRunning;
  public void addNode(BTNode n) {
    children.add(n);
  }
  public ArrayList<BTNode> shuffle() {
    Collections.shuffle(children);
    return children;
  }
  public void setLast(BTNode n) {
    lastRunning = n;
  }
}
