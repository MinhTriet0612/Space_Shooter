package org.example.world;

import java.awt.Graphics;
import java.util.*;

import org.example.IGraphic;
import org.example.entity.BaseObject;
import org.example.entity.Bullet;
import org.example.entity.Entity;
import org.example.entity.Monster;
import org.example.entity.MortalEntity;
import org.example.entity.Ship;
import org.example.item.Item;
import org.example.scene.CasualPlayScene;
import org.example.system.GameSystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class World {
  private final HashMap<Class<?>, List<?>> worldContainer;

  public World() {
    this.worldContainer = new HashMap<Class<?>, List<?>>();
    this.worldContainer.put(Ship.class, new LinkedList<Ship>());
    this.worldContainer.put(Bullet.class, new LinkedList<Bullet>());
    this.worldContainer.put(Monster.class, new LinkedList<Monster>());
    this.worldContainer.put(GameSystem.class, new LinkedList<GameSystem>());
  }

  public void update(float deltaTime) {
    for (List<?> list : worldContainer.values())
      for (Object object : list) {
        if (object instanceof IGraphic iGraphic)
          iGraphic.update(deltaTime);
      }
  }

  public void render(Graphics g) {
    for (Map.Entry<Class<?>, List<?>> entry : worldContainer.entrySet()) {
      if (entry.getKey().isAssignableFrom(Entity.class))
        ((List<Entity<?>>) entry.getValue()).forEach(entity -> {
            if(entity.isVisible()) {
              entity.render(g);
              entity.afterRender(g);
            }
        });
    }
  }

  public <S extends BaseObject> void removeFromWorld(S object) {
    for (Map.Entry<Class<?>, List<?>> entry : worldContainer.entrySet())
      if(object.getClass().isAssignableFrom(entry.getKey())) {
        ((List<S>) this.worldContainer.get(Monster.class)).remove(object);
      }
  }

  public <S extends BaseObject> void addToWorld(S object) {
    for (Map.Entry<Class<?>, List<?>> entry : worldContainer.entrySet()) 
      if(object.getClass().isAssignableFrom(entry.getKey())) {
        ((List<S>) this.worldContainer.get(Monster.class)).add(object);
      }
  }

  public List<Entity<?>> getEntities() {
    List<Entity<?>> entities = new LinkedList<>();
    for (Map.Entry<Class<?>, List<?>> entry : worldContainer.entrySet()) {
      if (entry.getKey().isAssignableFrom(Entity.class)) {
        entities.addAll((Collection<? extends Entity<?>>) ((LinkedList<Entity>) entry.getValue()));
      }
    }
    return entities;
  }

  // public <E extends Entity<?>> E addEntity(E entity) {
  //   this.entities.add(entity);
  //   entity.setWorld(this);
  //   if(entity instanceof MortalEntity mortalEntity) {
  //     mortalEntity.getWeapon().setWorld(this);
  //     mortalEntity.startTimer();
  //   }
  //   entity.onAdd();
  //   return entity;
  // }

  // public <S extends GameSystem> S addSystem(S system) {
  //   this.systems.add(system);
  //   system.setWorld(this);
  //   return system;
  // }

  public <I extends Item<?>> I addItem(I item) {
    item.setWorld(this);
    return item;
  }
}
