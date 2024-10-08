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

  @Override
  public Bullet fire(Vector2D position) {
    if (this.status.getCurrentStats().getAmmunition() <= 0) {
      this.setReload(true);
      this.reloadAmmunition.start();
      this.status.getCurrentStats().setAmmunition(this.status.getInitStats().getAmmunition());
    } else if (this.isFiring() && !this.isReload()) {
      this.setFiring(false);
      this.status.getCurrentStats().setAmmunition(this.status.getCurrentStats().getAmmunition() - 1);
      this.reloadForNextBullet.stop();
      Bullet bullet = new Bullet();
      bullet.setPosition(position);
      return bullet;
    } else {
      this.reloadAmmunition.start();
      this.reloadForNextBullet.start();
    }
    return null;
  }

  public void reloadForNextBullet() {
    this.setFiring(true);
  }

  public void reloadAmmunition() {
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
