package org.example.entities;

import java.awt.*;

public abstract class Entity {
    protected int x, y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract public void render(Graphics g);


}
