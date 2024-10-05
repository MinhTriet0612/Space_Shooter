package org.example.system.controller;

import java.util.ListIterator;

import org.example.builder.MonsterBuilder;
import org.example.common.ScreenAttributeConstant;
import org.example.entities.Entity;
import org.example.entities.Monster;
import org.example.system.GameSystem;

// LevelSystem, Monster Wave Here
public class MonsterSponsor extends GameSystem {
    @Override
    public void update(float deltaTime) {
        if (this.world.getEntities().size() == 1) {
            ListIterator<Entity<?>> entityIt = this.world.getEntities().listIterator();
            ListIterator<GameSystem> systemIt = this.world.getSystems().listIterator();

            for (int i = 0; i < ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH; i += ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH / 6) {
                int y = (int) Math.round(Math.random() * i);
                Monster monster = MonsterBuilder.monsterBuilder(i, -1 * y - 10);
                MonsterController monsterController = new MonsterController(monster);
                monsterController.setWorld(this.world);
                entityIt.add(monster);
                // systemIt.add(monsterController);
            }
        }
    }
}