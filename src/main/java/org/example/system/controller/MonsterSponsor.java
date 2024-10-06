package org.example.system.controller;

import org.example.builder.MonsterBuilder;
import org.example.common.ScreenAttributeConstant;
import org.example.entities.Entity;
import org.example.entities.Monster;
import org.example.system.GameSystem;

import java.util.*;

// LevelSystem, Monster Wave Here
public class MonsterSponsor extends ControllerSystem {
    @Override
    public void update(float deltaTime) {
        int instanceOfMonster = (int) this.world.getEntities().stream().filter(entity -> entity instanceof Monster).count();
        if (instanceOfMonster != 0) return;
        for (int i = 0; i < ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH; i += ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH / 6) {
            int y = (int) Math.round(Math.random() * i);
            Monster monster = MonsterBuilder.monsterBuilder(i, -1 * y - 10);
            monster.setWorld(world);
            MonsterObserver monsterObserver = new MonsterObserver(monster);
            this.world.getEntities().add(monster);
            this.world.getSystems().add(monsterObserver);
        }
    }
}