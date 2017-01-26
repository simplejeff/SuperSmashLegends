package jmhao.engine.Graph;

public interface NodeEdge {
  public Node getStart();
  public Node getEnd();
  public int getWeight();
  public int getTotalWeight();
}
