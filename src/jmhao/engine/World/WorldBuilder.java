package jmhao.engine.World;

import java.util.HashMap;

import cs195n.LevelData;
import cs195n.LevelData.ConnectionData;
import cs195n.LevelData.EntityData;
import jmhao.engine.Application;
import jmhao.engine.Entity.Connection;
import jmhao.engine.Entity.Input;
import jmhao.engine.Entity.Output;
import jmhao.engine.Entity.PhysicsEntity;

/*
 * 
 * worldBuilder takes in LevelBuilder, application, creates a world.
 * 
 */
public abstract class WorldBuilder<T extends PhysicsEntity<T>> {
  private HashMap<String, T> entities = new HashMap<String, T>();
  private LevelData levelData;
  private Application app;
  public WorldBuilder(LevelData ld, Application a) {
    levelData = ld;
    app = a;
  }
  public Application getApp() {
    return app;
  }
  public LevelData getLevelData() {
    return levelData;
  }
  public void parseEntities() {
    for(EntityData e : levelData.getEntities()) {
      T entity = buildEntity(e);
      entities.put(e.getName(), entity);
    }
  }
  public abstract T buildEntity(EntityData e);
  public void parseConnections() {
    for(ConnectionData c : levelData.getConnections()) {
      T source = entities.get(c.getSource());
      if(source != null) {
        Output o = source.getOutput(c.getSourceOutput());
        T target = entities.get(c.getTarget());
        if(target != null) {
          Input i = target.getInput(c.getTargetInput());
          if(i != null && o != null) {
            Connection con = new Connection(i, c.getProperties());
            o.connect(con);
          }
        }
      }
    }
  }
}
