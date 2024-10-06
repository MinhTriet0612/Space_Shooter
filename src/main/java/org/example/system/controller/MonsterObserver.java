package org.example.system.controller;

import org.example.entity.Monster;
import org.example.system.GameSystem;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MonsterObserver extends GameSystem {
  private Monster monster;
  private static final List<Boolean> fireRate = List.of(true, false, false, false);

  @Override
  public void update(float d) {
    if (fireRate.get((int) Math.round(Math.random() * (fireRate.size() - 1))))
      this.monster.useWeapon();
  }
}
