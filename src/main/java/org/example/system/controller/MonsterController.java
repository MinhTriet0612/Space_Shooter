package org.example.system.controller;

import java.util.ListIterator;

import org.example.entities.Bullet;
import org.example.entities.Entity;
import org.example.entities.Monster;
import org.example.entities.Ship;
import org.example.input.ShipControlInput;
import org.example.system.GameSystem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MonsterController extends GameSystem {
    private Monster monster;
    
    @Override
    public void update(float d) {
        ListIterator<Entity<?>> entitiesIt = this.getWorld().getEntities().listIterator();
        Bullet bullet = this.monster.useWeapon();
        if (bullet != null) entitiesIt.add(bullet);
    }
}
