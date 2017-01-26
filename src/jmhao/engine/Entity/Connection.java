package jmhao.engine.Entity;

import java.util.HashMap;
import java.util.Map;

public class Connection {
  private Input target;
  private HashMap<String, String> args;
  public Connection(Input i, Map<String, String> arg) {
    target = i;
    args = new HashMap<String, String>(arg);
    //args = (HashMap<String, String>) arg;
  }
  public void run() { target.run(args); }
}
