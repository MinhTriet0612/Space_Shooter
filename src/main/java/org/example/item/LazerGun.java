package org.example.item;

import org.example.builder.BulletBuilder;
import org.example.entity.Bullet;
import org.example.stats.LazerGunStats;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.swing.Timer;

@Getter
@Setter
@SuperBuilder
public class LazerGun extends Weapon<LazerGunStats> {
  private final Timer reloadAmmunition = new Timer(4000, e -> this.reloadAmmunition());
  private final Timer reloadForNextBullet = new Timer(150, e -> this.reloadForNextBullet());

  @Override
  public Bullet fire(Vector2D position) {
    if (this.status.getCurrentStast().getAmmunition() <= 0) {
      this.isReload = true;
      this.reloadAmmunition.start();
      this.status.getCurrentStast().setAmmunition(this.status.getInitStast().getAmmunition());
    } else if (this.isFiring && !isReload) {
      this.isFiring = false;
      this.status.getCurrentStast().setAmmunition(this.status.getCurrentStast().getAmmunition() - 1);
      this.reloadForNextBullet.stop();
      return BulletBuilder.bulletBuilder(position);
    } else {
      this.reloadAmmunition.start();
      this.reloadForNextBullet.start();
    }
    return null;
  }

  public void reloadForNextBullet() {
    this.isFiring = true;
  }

  public void reloadAmmunition() {
    this.isReload = false;
  }

  @Override
  public boolean setAuto() {
    throw new UnsupportedOperationException("Unimplemented method 'setAuto'");
  }

  @Override
  public void update(float deltaTime) {
  }
}
