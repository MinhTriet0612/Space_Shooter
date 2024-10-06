package org.example.world;

import java.awt.Graphics;

import java.util.*;

import org.example.entities.Entity;
import org.example.system.GameSystem;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class World {
    protected LinkedList<GameSystem> systems = new LinkedList<>();
    protected LinkedList<Entity<?>> entities = new LinkedList<>();
    public abstract void render(Graphics g);
    public abstract void update(float deltaTime);
}