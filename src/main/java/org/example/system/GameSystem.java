package org.example.system;

import java.awt.Graphics;

import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @SuperBuilder
public abstract class GameSystem {
    protected World world;
    public abstract void update(float deltaTime);
    public abstract void render(Graphics g);
}