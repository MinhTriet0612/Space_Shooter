package org.example.world;

import java.awt.Graphics;
import java.util.ListIterator;

import org.example.common.ScreenAttributeConstant;
import org.example.entities.Entity;
import org.example.system.GameSystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CasualWorld extends World {
    @Override
    public void update(float deltaTime) {
        ListIterator<GameSystem> systemIt = this.systems.listIterator();
        ListIterator<Entity<?>> entityIt = this.entities.listIterator();

        while(entityIt.hasNext()) {
            Entity<?> e = entityIt.next();
            e.update(1f);
            if (
                    e.getPosition().getY() <= -1 * ScreenAttributeConstant.CASUALPLAYSCENE_HEIGHT
                    || e.getPosition().getY() >= 1 * ScreenAttributeConstant.CASUALPLAYSCENE_HEIGHT
                    // || e instanceof MortalEntity mortalE && mortalE.isDead()
                ) entityIt.remove();
        }

        while(systemIt.hasNext()) {
            GameSystem e = systemIt.next();
            e.update(1f);
        }
    }

    @Override
    public void render(Graphics g) {
        this.entities.stream().forEach(entity -> entity.render(g));
    }
}