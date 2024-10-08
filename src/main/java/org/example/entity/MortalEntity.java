package org.example.entity;

import org.example.item.Weapon;
import org.example.stats.MortalEntityStats;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class MortalEntity<S extends MortalEntityStats> extends Entity<S> {
  private Weapon<?> weapon;

  public MortalEntity() {
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
