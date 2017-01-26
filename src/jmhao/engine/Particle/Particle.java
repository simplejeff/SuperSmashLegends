package jmhao.engine.Particle;

import cs195n.Vec2f;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;


public class Particle {
  private Vec2f location, velocity;
  private Vec2f acceleration = new Vec2f(0.0f, .01f);
  float lifespan;
  float maxLifespan;
  Color c;
  public Particle(Vec2f location, Vec2f velocity, float lifespan, float maxLife, Color c) {
    this.c = c;
    this.velocity = velocity;
    this.location = location;
    this.lifespan = lifespan;
    this.maxLifespan = maxLife;
  }
  public boolean draw(Graphics2D g) {
    g.setColor(c);
    velocity = velocity.plus(acceleration);
    location = location.plus(velocity);
    lifespan -= 2.0;
    if(lifespan < 0.0f) {
      lifespan = 0.1f;
    }
    float alpha = lifespan/maxLifespan;
    AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    g.setComposite(alcom);
    g.fill(new Ellipse2D.Double((double)location.x, (double)location.y, 5, 5));
    return (lifespan < 0.0f);
  }
}
