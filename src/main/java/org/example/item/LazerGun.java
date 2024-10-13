package org.example.item;

import org.example.entity.Bullet;
import org.example.stats.LazergunStats;
import org.example.system.status.Status;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

import javax.swing.Timer;

@Getter
@Setter
public class Lazergun extends Weapon<LazergunStats> {
  private Status<LazergunStats> status = new Status<LazergunStats>(new LazergunStats());

  public Lazergun() {
  }

  @Override
  public void fire() {
    Bullet bullet = new Bullet();
    Vector2D velocity = Vector2D.fromAngle(this.getOwner().getRotation())
        .add(new Vector2D((float) Math.random() * 1f - 0.5f, (float) Math.random() * 1f - 0.5f));
    Vector2D position = this.getOwner().getPosition().clone().add(velocity.scale(50));
    bullet.setPosition(position.getX(), position.getY());
    bullet.setVelocity(velocity.scale(4));
    bullet.setType(0);

    this.getWorld().addEntity(bullet);
  }
}
