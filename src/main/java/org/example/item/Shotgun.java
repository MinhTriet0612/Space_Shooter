package org.example.item;

import org.example.entity.Bullet;
import org.example.stats.ShotgunStats;
import org.example.system.status.Status;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shotgun extends Weapon<ShotgunStats> {
  private Status<ShotgunStats> status = new Status<ShotgunStats>(new ShotgunStats());

  @Override
  public void fire() {
    for (int i = -3; i <= 3; i += 2) {
      Bullet bullet = new Bullet();
      Vector2D velocity = Vector2D.fromAngle(this.getOwner().getRotation());
      bullet.setPosition(this.getOwner().getPosition().getX(), this.getOwner().getPosition().getY());
      bullet.setVelocity(new Vector2D(velocity.scale(0.5f).getX() + i, velocity.scale(10).getY()));
      bullet.getPosition().add(Vector2D.fromAngle(this.getOwner().getRotation()).scale(50));
      bullet.setType(1);
      this.getWorld().addEntity(bullet);
    }
  }
}
