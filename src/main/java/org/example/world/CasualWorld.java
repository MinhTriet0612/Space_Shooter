package org.example.world;

import org.example.system.collision.CollisionSystem;
import org.example.system.mob.MonsterObserver;
import org.example.system.mob.MonsterSpawner;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CasualWorld extends World {
  public CasualWorld() {
    super();

    this.addSystem(new MonsterSpawner());
    this.addSystem(new MonsterObserver());
    this.addSystem(new CollisionSystem());
  }
}
