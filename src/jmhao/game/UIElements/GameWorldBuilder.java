package jmhao.game.UIElements;

import java.util.HashMap;
import java.util.List;

import cs195n.LevelData;
import cs195n.LevelData.EntityData;
import cs195n.LevelData.ShapeData;
import cs195n.Vec2i;
import jmhao.engine.Application;
import jmhao.engine.Entity.Door;
import jmhao.engine.Entity.PhysicsEntity;
import jmhao.engine.Entity.Relay;
import jmhao.engine.Entity.Sensor;
import jmhao.engine.Entity.Timer;
import jmhao.engine.World.WorldBuilder;

public class GameWorldBuilder<T extends PhysicsEntity<T>> extends WorldBuilder<T>{
  //private HashMap<String, T> gameEntities = new HashMap<String, T>();
  //private HashMap<String, T> engineEntities = new HashMap<String, T>();
  //private ArrayList<T> breakable = new ArrayList<T>();
  //private ArrayList<T> noCollision = new ArrayList<T>();
  GameWorld<T> myGW;
  public GameWorldBuilder(LevelData ld, Application a, Vec2i screenSize) {
    super(ld, a);
    myGW = new GameWorld<T>(screenSize);
  }
  public GameWorld<T> getGameWorld() {
    return myGW;
  }
  @SuppressWarnings("unchecked")
  @Override
  public T buildEntity(EntityData e) {
    String className = e.getEntityClass();
    HashMap<String, String> properties = new HashMap<String, String>(e.getProperties());
    //HashMap<String, String> properties = (HashMap<String, String>) e.getProperties();
    List<? extends ShapeData> s = e.getShapes();
    ShapeData aab;
    T currEntity;
    switch (className) {
      case "Sensor" :
        aab = s.get(0);
        Boolean visible = java.lang.Boolean.parseBoolean(properties.get("visible"));
        currEntity = (T)new Sensor<T>(aab.getMin(), aab.getWidth(), aab.getHeight(), visible);
        currEntity.setMass(10f);
        currEntity.setRest(0f);
        if(!visible) {
          myGW.addNoCollision(currEntity);
          //noCollision.add(currEntity);
        }
        myGW.addEngineEntity(currEntity);
        //engineEntities.put(name, currEntity);
        return currEntity;
      case "Relay" :
        aab = s.get(0);
        currEntity = (T)new Relay<T>(aab.getMin(), aab.getWidth(), aab.getHeight());
        currEntity.setMass(10f);
        currEntity.setRest(0f);
        myGW.addEngineEntity(currEntity);
        return currEntity;
        //engineEntities.put(name, currEntity);
      case "Door" :
        aab = s.get(0);
        currEntity = (T)new Door<T>(aab.getMin(), aab.getWidth(), aab.getHeight());
        currEntity.setMass(10f);
        currEntity.setRest(1f);
        myGW.addEngineEntity(currEntity);
        return currEntity;
        //engineEntities.put(name, currEntity);
      case "Timer" :
        currEntity = (T)new Timer<T>(Long.parseLong(properties.get("start")));
        currEntity.setMass(10f);
        currEntity.setRest(0f);
        myGW.addEngineEntity(currEntity);
        myGW.addNoCollision(currEntity);
        return currEntity;
        //engineEntities.put(name, currEntity);
        //noCollision.add(currEntity);
      case "Box" :
        aab = s.get(0);
        currEntity = (T)new Box<T>(aab.getMin(), aab.getWidth(), aab.getHeight());
        currEntity.setMass(10f);
        currEntity.setRest(1f);
        myGW.addEntity(currEntity);
        myGW.addBreakable(currEntity);
        return currEntity;
        //gameEntities.put(name, currEntity);
        //breakable.add(currEntity);
      case "Killbox" :
        aab = s.get(0);
        currEntity = (T)new Killbox<T>(aab.getMin(), aab.getWidth(), aab.getHeight());
        currEntity.setRest(Float.parseFloat(properties.get("rest")));
        myGW.addKillbox(currEntity);
        return currEntity;
        //gameEntities.put(name, currEntity);
      case "BackgroundHoriz" :
        aab = s.get(0);
        currEntity = (T)new BackgroundHoriz<T>(aab.getMin(), aab.getWidth(), aab.getHeight());
        currEntity.setRest(Float.parseFloat(properties.get("rest")));
        myGW.addEntity(currEntity);
        return currEntity;
        //gameEntities.put(name, currEntity);
      case "BackgroundVert" :
        aab = s.get(0);
        currEntity = (T)new BackgroundVert<T>(aab.getMin(), aab.getWidth(), aab.getHeight());
        currEntity.setRest(Float.parseFloat(properties.get("rest")));
        myGW.addEntity(currEntity);
        return currEntity;
        //gameEntities.put(name, currEntity);
      case "Player" :
        aab = s.get(0);
        Player<T> p = new Player<T>(myGW, aab.getMin(), aab.getWidth(), aab.getHeight());
        myGW.setPlayer(p);
        currEntity = (T)p;
        currEntity.setMass(Float.parseFloat(properties.get("mass")));
        currEntity.setRest(Float.parseFloat(properties.get("rest")));
        //myGW.addEntity(currEntity);
        return currEntity;
        //gameEntities.put(name, currEntity);
      case "Player2" :
        aab = s.get(0);
        Player2<T> p2 = new Player2<T>(myGW, aab.getMin(), aab.getWidth(), aab.getHeight());
        myGW.setPlayer2(p2);
        currEntity = (T)p2;
        currEntity.setMass(Float.parseFloat(properties.get("mass")));
        currEntity.setRest(Float.parseFloat(properties.get("rest")));
        //myGW.addEntity(currEntity);
        return currEntity;
      case "Spikes" :
        aab = s.get(0);
        currEntity = (T)new Spikes<T>(myGW, aab.getMin(), aab.getWidth(), aab.getHeight());
        currEntity.setMass(Float.parseFloat(properties.get("mass")));
        currEntity.setRest(Float.parseFloat(properties.get("rest")));
        myGW.addEntity(currEntity);
        return currEntity;
        //gameEntities.put(name, currEntity);
      case "WorldEnder" :
        currEntity = (T)new WorldEnder<T>(myGW);
        currEntity.setMass(10f);
        currEntity.setRest(0f);
        myGW.addEntity(currEntity);
        myGW.addNoCollision(currEntity);
        return currEntity;
      case "GravitySwitch" :
        currEntity = (T)new GravitySwitch<T>(myGW);
        currEntity.setMass(10f);
        currEntity.setRest(0f);
        myGW.addEntity(currEntity);
        myGW.addNoCollision(currEntity);
        return currEntity;
      default :     
        return null;
    }
  }

}
