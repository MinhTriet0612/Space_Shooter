package org.example.builder;

import org.example.entity.Monster;
import org.example.item.LazerGun;
import org.example.stats.LazerGunStats;
import org.example.stats.MonsterStats;
import org.example.stats.WeaponStats;
import org.example.system.status.Status;

import java.util.UUID;
import org.example.util.Vector2D;

public class MonsterBuilder {
  public static Monster monsterBuilder(int x, int y) {
    Monster monster = Monster.builder()
        .uuid(UUID.randomUUID())
        .status(new Status<>(MonsterStats.builder().health(100).build()))
        .position(new Vector2D(x, y))
        .isBoosting(0).direction(2).speed(2)
        .weapon(LazerGun.builder().status(
            new Status<WeaponStats>(LazerGunStats.builder().ammunition(10).build())).isReload(false).isFiring(true)
            .build())
        .build();
    monster.startTimer();
    return monster;
  }
}
