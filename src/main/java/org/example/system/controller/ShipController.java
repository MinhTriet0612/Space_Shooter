package org.example.system.controller;

import java.awt.Graphics;

import org.example.entities.Ship;
import org.example.input.ShipControlInput;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipController extends ControllerSystem {
    private Ship ship;
    private ShipControlInput input;

    @Override
    public void update(float d) {
        // System.out.println(this.ship.getPosition().getX() + " " + this.ship.getPosition().getY());
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
        // if (input.space) {
        //     fireLazerBolt();
        //     input.space = false;
        // }
    }

    @Override
    public void render(Graphics g) {
        this.ship.render(g);
    }
}
