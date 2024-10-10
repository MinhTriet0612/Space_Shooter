package org.example.world;

import java.awt.Graphics;
import java.util.LinkedList;

import org.example.entity.Entity;
import org.example.item.Item;
import org.example.scene.Scene;
import org.example.system.GameSystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class World {
  private Scene scene;
  private LinkedList<GameSystem<?>> systems = new LinkedList<>();
  private LinkedList<Entity<?>> entities = new LinkedList<>();

  public void update(float deltaTime) {
    for (int i = 0; i < this.systems.size(); i++) {
      this.systems.get(i).update(deltaTime);
    }

    for (int i = 0; i < this.entities.size(); i++) {
      this.entities.get(i).update(deltaTime);
    }

    for (int i = 0; i < this.entities.size(); i++) {
      if (this.entities.get(i).isMarkAsRemoved()) {
        this.entities.remove(i);
      }
    }
  }

  public void render(Graphics g) {
    for (int i = 0; i < this.getEntities().size(); i++) {
      Entity<?> entity = this.getEntities().get(i);
      if (entity.isVisible()) {
        entity.render(g);
        entity.afterRender(g);
      }
    }
  }

  public <E extends Entity<?>> E addEntity(E entity) {
    this.entities.add(entity);
    entity.setWorld(this);
    entity.onAdd();
    return entity;
  }

  public <S extends GameSystem> S addSystem(S system) {
    this.systems.add(system);
    system.setWorld(this);
    return system;
  }

  public <I extends Item<?>> I addItem(I item) {
    item.setWorld(this);
    return item;
  }
}
