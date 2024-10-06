package org.example.builder;

import org.example.entity.Bullet;
import org.example.rigid.Rectangle;
import org.example.stats.BulletStats;
import org.example.system.status.Status;
import org.example.util.Vector2D;
import java.util.UUID;

public class BulletBuilder {
  public static Bullet bulletBuilder(Vector2D position) {
    Bullet bullet = Bullet.builder()
        .uuid(UUID.randomUUID())
        .direct(0)
        .status(new Status<>(BulletStats.builder().build()))
        .position(position)
        .build();
    // bullet.getBulletAnimationTimer().start();
    return bullet;
  }
}
