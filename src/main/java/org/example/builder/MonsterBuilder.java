package org.example.builder;

import org.example.entities.Monster;
import org.example.stats.MonsterStats;
import org.example.system.status.Status;
import org.example.util.Vector2D;

public class MonsterBuilder {
    public static Monster monsterBuilder(int x, int y) {
        Monster monster = Monster.builder()
                .status(new Status<>(new MonsterStats()))
                .position(new Vector2D(x, y))
                .isBoosting(0).direction(2).speed(1).canFire(true)
                .fireRate(50).build();
        monster.startTimer();
        return monster;
    }
}