package org.example.builder;

import org.example.entities.Bullet;
import org.example.shape.Rectangle;
import org.example.stats.BulletStats;
import org.example.system.status.Status;
import org.example.util.Vector2D;

public class BulletBuilder {
    public static Bullet bulletBuilder(Vector2D position) {
        Bullet bullet = Bullet.builder()
                .direct(0)
                .status(new Status<>(BulletStats.builder().build()))
                .position(position)
                .rigid(new Rectangle(10, 20))
                .build();
        // bullet.getBulletAnimationTimer().start();
        return bullet;
    }
}