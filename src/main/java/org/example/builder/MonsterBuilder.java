package org.example.builder;

import org.example.entities.Monster;
import org.example.item.LazerGun;
import org.example.stats.MonsterStats;
import org.example.system.status.Status;
import org.example.util.Vector2D;

public class MonsterBuilder {
    public static Monster monsterBuilder(int x, int y) {
        Monster monster = Monster.builder()
                .status(new Status<>(MonsterStats.builder().health(100).build()))
                .position(new Vector2D(x, y))
                .isBoosting(0).direction(2).speed(2).canFire(true)
                .weapon(LazerGun.builder().build())
                .canFire(true)
                .fireRate(50).build();
        monster.startTimer();
        return monster;
    }
}