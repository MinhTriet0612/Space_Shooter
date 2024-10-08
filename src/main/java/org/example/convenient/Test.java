package org.example.convenient;

import org.example.entity.Monster;
import org.example.entity.Ship;
import org.example.item.LazerGun;
import org.example.item.Weapon;
import org.example.stats.LazerGunStats;
import org.example.stats.MonsterStats;
import org.example.stats.ShipStats;
import org.example.system.status.Status;
import org.example.util.Vector2D;

public class Test {
  public static Ship<?> genShip() {
    Ship<?> ship = Ship.builder()
        .status(new Status<ShipStats>(new ShipStats()))
        .position(new Vector2D(384, 514))
        .isBoosting(0)
        .velocity(new Vector2D(0, 0))
        .weapon(new LazerGun(new Status<LazerGunStats>(new LazerGunStats())))
        .build();
    ship.getStatus().getInitStats().setSpeed(6);
    ship.getWeapon().getStatus().getInitStats().setAmmunition(100);
    // ship.startTimer();
    // world.addEntity(ship);
    // ship.getWeapon().setWorld(world);
    return ship;
  }

  public static Weapon<LazerGunStats> genWeapon() {
    LazerGun lazerGun = new LazerGun(new Status<LazerGunStats>(new LazerGunStats()));
    lazerGun.setFiring(true);  
    lazerGun.setReload(false);
    return lazerGun ;
  }

  public static Monster genMonster() {
    Monster monster = Monster.builder()
        .status(new Status<MonsterStats>(new MonsterStats()))
        .position(new Vector2D(384, 514))
        .isBoosting(0)
        .velocity(new Vector2D(0, 0))
        .weapon(new LazerGun(new Status<LazerGunStats>(new LazerGunStats())))
        .build();
      monster.getWeapon().getStatus().getInitStats().setAmmunition(100);
      monster.getStatus().getInitStats().setSpeed(6);
    // ship.startTimer();
    // world.addEntity(ship);
    // ship.getWeapon().setWorld(world);
    return monster; 
  }
}
