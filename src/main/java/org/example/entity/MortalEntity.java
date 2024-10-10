package org.example.entity;

import javax.swing.Timer;

import org.example.item.Weapon;
import org.example.stats.MortalEntityStats;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class MortalEntity<S extends MortalEntityStats> extends Entity<S> {
  protected int isBoosting;
  private Weapon<?> weapon;
  private final Timer boostTimer = new Timer(130, e -> {
    this.updateShipBoost();
  });
  
  public void updateShipBoost() {
    this.isBoosting = (this.isBoosting == 0) ? 1 : 0;
  }
  
  public void update(float deltaTime) {
    super.update(deltaTime);
    if (this.isDead()) {
      this.onRemove();
    }
  }

  public void startTimer() {
    this.boostTimer.start();
  }

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
