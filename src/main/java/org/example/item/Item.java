package org.example.item;

import org.example.entity.BaseObject;
import org.example.stats.ItemStats;
import org.example.system.status.Status;
import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Item<T extends ItemStats> extends BaseObject {
  protected World world;
  protected Status<T> status;

  public abstract void update(float deltaTime);
}
