package org.example.system.mob;

import org.example.constant.ScreenAttributeConstant;
import org.example.entity.Monster;
import org.example.item.LazerGun;
import org.example.system.controller.ControllerSystem;

// LevelSystem, Monster Wave Here
public class MonsterSpawner extends ControllerSystem {
  @Override
  public void update(float deltaTime) {
    int instanceOfMonster = (int) this.getWorld().getEntities().stream().filter(entity -> entity instanceof Monster)
        .count();
    if (instanceOfMonster != 0)
      return;
    for (int i = 0; i < ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH; i += ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH
        / 6) {
      int y = (int) Math.round(Math.random() * i);
      Monster monster = new Monster();
      monster.setWeapon(this.getWorld().addItem(new LazerGun()));
      monster.setPosition(i, -1 * y - 10);
      // MonsterObserver monsterObserver = new MonsterObserver(monster);
      this.getWorld().addEntity(monster);
      // this.getWorld().addSystem(monsterObserver);
    }
  }
}
