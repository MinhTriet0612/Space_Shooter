package org.example.item;

import org.example.entity.Bullet;
import org.example.stats.LazerGunStats;
import org.example.system.status.Status;
import org.example.util.DeepCopyUtils;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

import javax.swing.Timer;

@Getter
@Setter
public class GatlinGun extends Weapon<LazerGunStats> {
  private Status<LazerGunStats> status = new Status<LazerGunStats>(new LazerGunStats());

  private final Timer reloadAmmunition = new Timer(4000, e -> this.reloadAmmunition());
  private final Timer reloadForNextBullet = new Timer(150, e -> this.reloadForNextBullet());

  @Override
  public void fire(Vector2D position, Vector2D velocity) {
    LazerGunStats initStats = this.status.getInitStats(),
        currentStats = this.status.getCurrentStats();

    if (!isFiring() || isReload()) return;

    if (currentStats.getAmmunition() <= 6) {
      this.setReload(true);
      this.reloadAmmunition.start();
      currentStats.setAmmunition(initStats.getAmmunition());
    }

    this.setFiring(false);
    this.reloadForNextBullet.start();
    currentStats.setAmmunition(currentStats.getAmmunition() - 6);
    for(int i = -6; i <= 6; i+= 2) {
      Bullet bullet = new Bullet();
      bullet.setPosition(DeepCopyUtils.copy(position));
      bullet.getPosition().plus(velocity.scale(5));
      bullet.setVelocity(new Vector2D(velocity.getX() + i, velocity.getY()));
      bullet.setType(1);
      this.getWorld().addEntity(bullet);
    }
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
