package org.example.item;

import org.example.entity.Bullet;
import org.example.stats.WeaponStats;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Weapon<S extends WeaponStats> extends Item<S> {
  private boolean isFiring = true, isReload = false;

  public Weapon() {
  }

  public abstract boolean setAuto();

  public abstract void fire(Vector2D position, Vector2D velocity);
}
