package org.example.world;

import java.awt.Graphics;
import java.util.ArrayList;

import org.example.entity.Entity;
import org.example.system.GameSystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class World {
  protected ArrayList<GameSystem> systems = new ArrayList<>();
  protected ArrayList<Entity<?>> entities = new ArrayList<>();

  public abstract void render(Graphics g);

  public void update(float deltaTime) {

    for (int i = 0; i < this.systems.size(); i++) {
      this.systems.get(i).update(deltaTime);
    }

    for (int i = 0; i < this.entities.size(); i++) {
      this.entities.get(i).update(deltaTime);
    }
  }

  public void addEntity(Entity<?> entity) {
    this.entities.add(entity);
    entity.setWorld(this);
  }

  public void addSystem(GameSystem system) {
    this.systems.add(system);
    system.setWorld(this);
  }
}
