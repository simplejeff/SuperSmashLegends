package jmhao.engine.Entity;

import cs195n.Vec2f;
import cs195n.Vec2i;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import jmhao.engine.Shape.Shape;

public abstract class Entity {
    protected Shape s;
    private HashMap<String, Output> outputs = new HashMap<String, Output>();
    private HashMap<String, Input> inputs = new HashMap<String, Input>();
    /**
     * Update state changes over time
     * 
     * @param millisSincePreviousTick  approximate number of milliseconds since the previous call
     *                                to onTick
     */
    public void addOutput(Output o, String s) {
      outputs.put(s, o);
    }
    public void addInput(Input i, String s) {
      inputs.put(s, i);
    }
    public Output getOutput(String s) {
      return outputs.get(s);
    }
    public Input getInput(String s) {
      return inputs.get(s);
    }
    public abstract void onTick(long millisSincePreviousTick);
    public abstract void changePos(Vec2i change);
    public abstract void reset();
    public abstract void changeColor(Color newC);
    /**
     * Called when the screen needs to be redrawn.
     * 
     * @param g   a {@link Graphics2D} object used for drawing.
     */
    public abstract void drawElement(Graphics2D g);
    
    /**
     * @param e   an AWT {@link KeyEvent} representing the input event.
     * @see KeyListener#keyPressed(KeyEvent)
     */
    public abstract void onKeyPressed(KeyEvent e);
    public abstract void onKeyReleased(KeyEvent e);
    /**
     * Called when the size of the drawing area changes. Any subsequent calls to onDraw should note
     * the new size and be sure to fill the entire area appropriately. Guaranteed to be called
     * before the first call to onDraw.
     * 
     * @param newSize the new size of the drawing area.
     */
    public abstract void setSize(Vec2i newSize);
    public abstract boolean isSelected(Point p);
    public abstract void setSelected(boolean s);
    public Shape getShape() {
      return s;
    }
    public void setShape(Shape newS) {
      s = newS;
    }
    public abstract EntityType getEntity();
}
