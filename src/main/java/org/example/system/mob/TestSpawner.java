package org.example.system.mob;

import org.example.entity.EnemyShip;
import org.example.graphic.Healthbar;
import org.example.item.Lazergun;
import org.example.stats.SystemStats;
import org.example.system.GameSystem;
import java.util.LinkedList;

// LevelSystem, Monster Wave Here
public class TestSpawner extends GameSystem<SystemStats> {
  @Override
  public void update(float deltaTime) {
    LinkedList<EnemyShip> enemyShips = this.queryEntities(EnemyShip.class);
    int count = enemyShips.size();
    if (count > 5) {
      return;
    }
    EnemyShip enemyShip = new EnemyShip();
    enemyShip.setWeapon(this.getWorld().addItem(new Lazergun()));
    enemyShip.setPosition(this.getWorld().getScene().getSize().width / 2 + 10 * count, 100);
    this.getWorld().addEntity(enemyShip);
  }
}
