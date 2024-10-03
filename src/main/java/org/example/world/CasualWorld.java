package org.example.world;

import java.awt.Graphics;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @SuperBuilder
public class CasualWorld extends World {
    @Override
    public void update(float deltaTime) {
        this.entities.stream().forEach(entity -> entity.update(1f));
        this.systems.stream().forEach(gameSystem -> gameSystem.update(1f));
    }

    @Override
    public void render(Graphics g) {
        this.entities.stream().forEach(entity -> entity.render(g));
        this.systems.stream().forEach(gameSystem -> gameSystem.render(g));
    }
}