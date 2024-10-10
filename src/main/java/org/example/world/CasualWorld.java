package org.example.world;

import org.example.system.collision.CollisionSystem;
import org.example.system.controller.MonsterSpawner;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CasualWorld extends World {
  public CasualWorld() {
    super();
    this.addSystem(new MonsterSpawner());
    this.addSystem(new CollisionSystem());
  }
}
