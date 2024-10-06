package org.example.system.controller;

import org.example.builder.MonsterBuilder;
import org.example.constant.ScreenAttributeConstant;
import org.example.entity.Monster;

// LevelSystem, Monster Wave Here
public class MonsterSpawner extends ControllerSystem {
  @Override
  public void update(float deltaTime) {
    int instanceOfMonster = (int) this.world.getEntities().stream().filter(entity -> entity instanceof Monster)
        .count();
    if (instanceOfMonster != 0)
      return;
    for (int i = 0; i < ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH; i += ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH
        / 6) {
      int y = (int) Math.round(Math.random() * i);
      Monster monster = MonsterBuilder.monsterBuilder(i, -1 * y - 10);
      MonsterObserver monsterObserver = new MonsterObserver(monster);
      this.world.addEntity(monster);
      this.world.addSystem(monsterObserver);
    }
  }
}
