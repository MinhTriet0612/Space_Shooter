package org.example.game;

import java.awt.Graphics;

import org.example.system.controller.ShipController;

public class GameStateManager {
    private ShipController system;
    // private LinkedList<LazerBolt> lazerBolts;

    // public GameStateManager(Input controlInp) {
    //     Ship ship = new Ship(new Rectangle(50, 80), null);
    //     ship.setPosition(new Vector2D(100, 200));
    //     this.system = new ShipController(ship, controlInp);
    // }

    public void update() {
        system.update(1f);
        // Iterator<LazerBolt> iterator = lazerBolts.iterator();
        // while (iterator.hasNext()) {
        //     LazerBolt lazer = iterator.next();
        //     lazer.update();

        //     if (lazer.isOutOfBounds())
        //         iterator.remove();
        // }
    }

    public void render(Graphics g) {
        // system.render(g);
        // Iterator<LazerBolt> iterator = lazerBolts.iterator();
        // while (iterator.hasNext()) {
        //     LazerBolt lazer = iterator.next();
        //     lazer.render(g);
        // }
    }
}