package org.example.item;

import org.example.entity.Bullet;
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

  public LazerGun(Status<LazerGunStats> status) {
    this.status = status;
  }

  @Override
  public Bullet fire(Vector2D position) {
    LazerGunStats
      initStats = this.status.getInitStats(),
      currentStats = this.status.getCurrentStats();

    if(!isFiring() || isReload()) return null;

    if (currentStats.getAmmunition() == 1) {
      this.setReload(true);
      this.reloadAmmunition.start();
      currentStats.setAmmunition(initStats.getAmmunition());
    } 

    this.setFiring(false);
    this.reloadForNextBullet.start();
    currentStats.setAmmunition(currentStats.getAmmunition() - 1);
    return Bullet.builder().position(position).build();
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
