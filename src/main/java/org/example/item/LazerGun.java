package org.example.item;

import org.example.entity.Bullet;
import org.example.entity.Monster;
import org.example.stats.LazerGunStats;
import org.example.system.status.Status;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

import javax.swing.Timer;

@Getter
@Setter
public class LazerGun extends Weapon<LazerGunStats> {
  private Status<LazerGunStats> status = new Status<LazerGunStats>(new LazerGunStats());

  private final Timer reloadAmmunition = new Timer(4000, e -> this.reloadAmmunition());
  private final Timer reloadForNextBullet = new Timer(150, e -> this.reloadForNextBullet());

  public LazerGun() {
  }

  @Override
  public void fire(Vector2D position) {
    LazerGunStats initStats = this.status.getInitStats(),
        currentStats = this.status.getCurrentStats();

    if (!isFiring() || isReload())
      return;

    if (currentStats.getAmmunition() == 1) {
      this.setReload(true);
      this.reloadAmmunition.start();
      currentStats.setAmmunition(initStats.getAmmunition());
    }

    this.setFiring(false);
    this.reloadForNextBullet.start();
    currentStats.setAmmunition(currentStats.getAmmunition() - 1);
    Bullet bullet = new Bullet();
    bullet.setPosition(position.add(new Vector2D(0, -50)));
    bullet.setDirect(Math.random() > 0.5 ? 0 : 1);
    this.getWorld().addEntity(bullet);
  }

  public void reloadForNextBullet() {
    this.setFiring(true);
    this.reloadForNextBullet.stop();
  }

  public void reloadAmmunition() {
    this.reloadAmmunition.stop();
    this.setReload(false);
  }

  @Override
  public boolean setAuto() {
    throw new UnsupportedOperationException("Unimplemented method 'setAuto'");
  }

  @Override
  public void update(float deltaTime) {
  }
}
