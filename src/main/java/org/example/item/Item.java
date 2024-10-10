package org.example.item;

import org.example.BaseGameObject;
import org.example.stats.ItemStats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item<I extends ItemStats> extends BaseGameObject<I> {
  public Item() {
  }
}
