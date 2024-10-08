package org.example.entity;

import org.example.item.Weapon;
import org.example.stats.MortalEntityStats;
import org.example.system.status.Status;
import org.example.util.Vector2D;
import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class MortalEntity<S extends MortalEntityStats> extends Entity<S> {
  private Weapon<?> weapon;

  protected MortalEntity(World world, Vector2D position, Vector2D velocity, boolean markAsRemoved,
      boolean isCollidable, boolean isVisible, Weapon<?> weapon) {
    super(world, position, velocity, markAsRemoved, isCollidable, isVisible);
    this.weapon = weapon;
  }

  public boolean isDead() {
    return this.getStatus().getCurrentStats().getHealth() <= 0;
  }

  public abstract void useWeapon();

  public void injure(int damage) {
    this.getStatus().getCurrentStats()
        .setHealth(
            this.getStatus().getCurrentStats().getHealth() - (damage));
  }

  public void heal(int hp) {
    this.getStatus().getCurrentStats()
        .setHealth(
            Math.min(
                this.getStatus().getCurrentStats().getHealth() + hp,
                this.getStatus().getInitStats().getHealth()));
  }
}
