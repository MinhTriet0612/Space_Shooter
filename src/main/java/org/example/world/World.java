package org.example.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import org.example.entities.Entity;
import org.example.system.GameSystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class World {
    protected LinkedList<GameSystem> systems = new LinkedList<>();
    protected LinkedList<Entity<?>> entities = new LinkedList<>();
    public abstract void update(float deltaTime);
    public abstract void render(Graphics g);
}