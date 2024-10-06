package org.example.builder;

import org.example.entity.Ship;
import org.example.item.LazerGun;
import org.example.stats.LazerGunStats;
import org.example.stats.ShipStats;
import org.example.stats.WeaponStats;
import org.example.system.status.Status;
import org.example.util.Vector2D;
import java.util.UUID;

public class ShipBuilder {
  public static Ship shipBuilder() {
    Ship ship = Ship.builder()
        .uuid(UUID.randomUUID())
        .status(new Status<ShipStats>(ShipStats.builder().health(100).build()))
        .position(new Vector2D(384, 514))
        .isBoosting(0).direction(2).speed(6)
        .weapon(LazerGun.builder().status(
            new Status<WeaponStats>(LazerGunStats.builder().ammunition(10).build())).isReload(false).isFiring(true)
            .build())
        .build();
    ship.startTimer();
    return ship;
  }
}
