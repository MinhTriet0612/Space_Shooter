package org.example.world;

import java.awt.Graphics;

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
    // this.addSystem(new CollisionSystem());
  }

  @Override
  public void render(Graphics g) {
    for (int i = 0; i < this.getEntities().size(); i++) {
      this.getEntities().get(i).render(g);
    }
  }
}
