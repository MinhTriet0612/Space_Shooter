package org.example.entity;

import org.example.stats.MortalEntityStats;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class MortalEntity<S extends MortalEntityStats> extends Entity<S> {
  private boolean isDead = false;

  public MortalEntity() {
  }

  public void update(float deltaTime) {
    super.update(deltaTime);

    if (!this.isDead() && this.getStatus().getCurrentStats().getHealth() <= 0) {
      this.onDeath();
      this.setDead(true);
    }
  }

  public void onDeath() {
  }

  public void primaryAction() {
  }

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
