package org.example.item;

import org.example.Renderable;
import org.example.stats.WeaponStats;
import java.awt.Graphics;
import javax.swing.Timer;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Weapon<S extends WeaponStats> extends Item<S> implements Renderable {
  private boolean isCooldown = false;
  private boolean isReload = false;
  private boolean isFiring = false;
  private Timer reloadAmmunition = new Timer(1000, e -> this.reloadAmmunition());
  private Timer fireCooldownTimer = new Timer(10, e -> this.fireCooldown());

  public Weapon() {
    this.reloadAmmunition.setRepeats(false);
    this.fireCooldownTimer.setRepeats(false);
  }

  abstract public void fire();

  @Override
  public void onReady() {
    this.fireCooldownTimer.setInitialDelay(1000 / this.getCurrentStats().getFireRate());
  }

  public void reloadAmmunition() {
    this.getCurrentStats().setAmmunition(this.getInitStats().getAmmunition());
    this.setReload(false);
  }

  public void fireCooldown() {
    this.setCooldown(false);
  }

  public void update(float deltaTime) {
    if (this.isReload())
      return;

    if (this.getCurrentStats().getAmmunition() <= 0) {
      this.setReload(true);
      this.reloadAmmunition.start();
      return;
    }

    if (this.isFiring() && !this.isCooldown()) {
      this.fire();
      this.getCurrentStats()
          .setAmmunition(this.getCurrentStats().getAmmunition() - 1);
      this.setCooldown(true);
      this.fireCooldownTimer.start();
    }
  }

  public void render(Graphics g) {
  }
}
