package org.example.world;

import org.example.system.collision.CollisionSystem;
import org.example.system.mob.TestSpawner;
import java.awt.Graphics;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CasualWorld extends World {
  private TestSpawner testSpawner = new TestSpawner();
  private CollisionSystem collisionSystem = new CollisionSystem();

  public CasualWorld() {
    super();
    this.addSystem(this.testSpawner);
    this.addSystem(this.collisionSystem);
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    g.drawString("BigO collisions: " + this.collisionSystem.getLoopCount(), 20, 65);
  }
}
