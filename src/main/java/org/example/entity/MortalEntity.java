package org.example.entity;

import org.example.item.Weapon;
import org.example.stats.MortalEntityStats;
import org.example.world.World;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
public abstract class MortalEntity<S extends MortalEntityStats> extends Entity<S> {
  protected Weapon<?> weapon;

  public boolean isDead() {
    return this.status.getCurrentStats().getHealth() <= 0;
  }

  public abstract void useWeapon();

  public void injure(int damage) {
    this.status.getCurrentStats()
        .setHealth(
            this.status.getCurrentStats().getHealth() - (damage));
  }

  public void heal(int hp) {
    this.status.getCurrentStats()
        .setHealth(
            Math.min(
                this.status.getCurrentStats().getHealth() + hp,
                this.status.getInitStats().getHealth()));
  }
}
