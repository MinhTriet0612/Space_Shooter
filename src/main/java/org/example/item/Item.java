package org.example.item;

import org.example.BaseGameObject;
import org.example.entity.Entity;
import org.example.stats.ItemStats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item<I extends ItemStats> extends BaseGameObject<I> {
  private Entity<?> owner;

  public Item() {
  }

  public boolean isReady() {
    return super.isReady() && this.getOwner() != null;
  }

  public void setOwner(Entity<?> owner) {
    this.owner = owner;
    this.checkReady();
  }
}
