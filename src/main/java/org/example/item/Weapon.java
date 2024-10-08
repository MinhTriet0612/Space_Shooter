package org.example.item;

import org.example.entity.Bullet;
import org.example.stats.WeaponStats;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Weapon<S extends WeaponStats> extends Item<S> {
  private boolean isFiring, isReload;

  public abstract boolean setAuto();

  public abstract Bullet fire(Vector2D position);
}
