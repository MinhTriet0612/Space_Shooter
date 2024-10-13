package org.example.system;

import java.util.LinkedList;
import java.util.stream.Collectors;

import org.example.BaseGameObject;
import org.example.entity.Entity;
import org.example.stats.SystemStats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GameSystem<S extends SystemStats> extends BaseGameObject<S> {
  public <E extends Entity<?>> LinkedList<E> queryEntities(Class<E> entityClass) {
    return this.getWorld().getEntities().stream().filter(entity -> entity.getClass().equals(entityClass))
        .map(entity -> (E) entity).collect(Collectors.toCollection(LinkedList::new));
  }
}
