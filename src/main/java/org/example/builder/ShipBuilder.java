package org.example.builder;

import org.example.entities.Ship;
import org.example.stats.ShipStats;
import org.example.system.status.Status;
import org.example.util.Vector2D;

public class ShipBuilder {
    public static Ship shipBuilder() {
        ShipStats shipStats = ShipStats.builder().health(100).build();
        Ship ship = Ship.builder()
                .status(new Status<>(ShipStats.builder().health(100).build()))
                .position(new Vector2D(384, 514))
                .isBoosting(0).direction(2).speed(6).canFire(true)
                .fireRate(50).build();
        ship.startTimer();
        return ship;
    }
}