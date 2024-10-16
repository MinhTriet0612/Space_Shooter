package org.example.world;

import java.awt.Graphics;
import java.util.ArrayList;

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
  private ArrayList<GameSystem<?>> systems = new ArrayList<>();
  private ArrayList<Entity<?>> entities = new ArrayList<>();

  public static class Timer {
    private long lastTime = System.currentTimeMillis();
    private long currentTime = System.currentTimeMillis();
    private long deltaTime = 0;

    public void update() {
      this.currentTime = System.currentTimeMillis();
      this.deltaTime = this.currentTime - this.lastTime;
      this.lastTime = this.currentTime;
    }

    public long getDeltaTime() {
      return this.deltaTime;
    }
  }

  public void update(float deltaTime) {

    for (int i = 0; i < this.systems.size(); i++) {
      this.systems.get(i).update(deltaTime);
    }

    for (int i = 0; i < this.entities.size(); i++) {
      Entity<?> entity = this.getEntities().get(i);
      entity.beforeUpdate(deltaTime);
      entity.update(deltaTime);
      entity.afterUpdate(deltaTime);
    }

    for (int i = 0; i < this.entities.size(); i++) {
      Entity<?> entity = this.getEntities().get(i);
      if (entity.isMarkAsRemoved()) {
        entity.onRemove();
        this.entities.remove(i);
        i--;
      }
    }
  }

  public void render(Graphics g) {
    for (int i = 0; i < this.getEntities().size(); i++) {
      Entity<?> entity = this.getEntities().get(i);
      if (entity.isVisible()) {
        entity.beforeRender(g);
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
