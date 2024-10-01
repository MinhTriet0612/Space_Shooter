package org.example.entities;

import java.awt.Graphics;

import org.example.item.Weapon;

public abstract class Ship extends Entity {

    protected Weapon weapon;

    protected HealthSystem health;

    public Ship(int x, int y, Weapon weapon, HealthSystem health) {
        super(x, y);
        this.weapon = weapon;
        this.health = health;
    }

    abstract public void render(Graphics g);

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void intersects() {
        
    }
    
}