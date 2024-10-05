package org.example.system.controller;

import java.util.ListIterator;

import org.example.entities.Bullet;
import org.example.entities.Entity;
import org.example.entities.Ship;
import org.example.input.ShipControlInput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipController extends ControllerSystem {
    private Ship ship;
    private ShipControlInput input; 

    @Override
    public void update(float d) {
        if (this.input.isUp()) {
            this.ship.moveUp();
        } else if (input.isDown()) {
            this.ship.moveDown();
        } else if (input.isLeft()) {
            this.ship.moveLeft();
        } else if (input.isRight()) {
            this.ship.moveRight();
        } else {
            this.ship.reRenderDirection();
        } 
        if (input.isSpace()) {
            ListIterator<Entity<?>> entitiesIt = this.getWorld().getEntities().listIterator();
            Bullet bullet = this.ship.useWeapon();
            if (bullet != null) entitiesIt.add(bullet);
        }
    }
}