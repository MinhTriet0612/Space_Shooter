package org.example.world;

import java.awt.Graphics;
import java.util.ListIterator;

import org.example.common.ScreenAttributeConstant;
import org.example.entities.Entity;
import org.example.entities.MortalEntity;
import org.example.system.GameSystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CasualWorld extends World {
    @Override
    public synchronized void update(float deltaTime) {
        for (int i = 0; i < this.entities.size(); i++) {
            Entity<?> entity = this.entities.get(i);
            entity.update(1f);
            if (
                entity.getPosition().getY() <= -1 * ScreenAttributeConstant.CASUALPLAYSCENE_HEIGHT
                || entity.getPosition().getY() >= 1 * ScreenAttributeConstant.CASUALPLAYSCENE_HEIGHT
                || entity instanceof MortalEntity mortalE && mortalE.isDead()
            ) this.entities.remove(i);
        }

        for (int i = 0; i < this.systems.size(); i++) this.systems.get(i).update(1f);
    }

    @Override
    public synchronized void render(Graphics g) {
        for (int i = 0; i < this.entities.size(); i++) this.entities.get(i).render(g);
    }
}