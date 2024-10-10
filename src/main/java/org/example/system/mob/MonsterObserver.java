package org.example.system.mob;

import org.example.entity.Monster;
import org.example.system.GameSystem;

import java.util.*;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MonsterObserver extends GameSystem {
  private static final List<Boolean> fireRate = List.of(true, false, false, false);

  @Override
  public void update(float deltaTime) {
    this.getWorld().getEntities().stream().filter(entity -> entity instanceof Monster).collect(Collectors.toList())
        .forEach(entity -> {
          Monster monster = (Monster) entity;
          if (fireRate.get((int) Math.round(Math.random() * (fireRate.size() - 1))) && monster.getWeapon() != null) {
            monster.useWeapon();
          }
        });
  }
}
