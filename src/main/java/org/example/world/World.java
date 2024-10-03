package org.example.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.example.entities.Entity;
import org.example.system.GameSystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @SuperBuilder
public abstract class World {
    protected List<GameSystem> systems = new ArrayList<>();
    protected List<Entity<?>> entities = new ArrayList<>();
    public abstract void update(float deltaTime);
    public abstract void render(Graphics g);
}