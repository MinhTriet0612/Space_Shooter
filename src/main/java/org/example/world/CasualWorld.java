package org.example.world;

import org.example.system.collision.CollisionSystem;
import org.example.system.mob.TestSpawner;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CasualWorld extends World {
  public CasualWorld() {
    super();
    this.addSystem(new TestSpawner());
    this.addSystem(new CollisionSystem());
  }
}
