package jmhao.engine.Graph;

import java.util.Set;

public interface Node {
  public Node getPrev();
  /**
   * gets edges from this node
   * @param visited visited nodes
   * @param totalWeight total weight currently
   * @param dest destination node
   * @return edges from this node
   */
  public Set<NodeEdge> getEdges(Set<Node> visited, int totalWeight, Node dest);
  /**
   * sets previous node
   * @param t previous node
   */
  public void setPrev(Node t);
  /**
   * gets weight to another node
   * @param t another node
   * @return int of the weight
   */
  public int getWeight(Node t);
  /**
   * gets Nodes next to this node
   * @return set of nodes next to this node
   */
  public Set<Node> getNodesAround();
  /**
   * equality with another node
   * @param n other node
   * @return equality
   */
  public boolean equals(Node n);
}
