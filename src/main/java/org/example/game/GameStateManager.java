package org.example.game;

import org.example.entities.HealthSystem;
import org.example.entities.LazerBolt;
import org.example.entities.PlayerShip;
import org.example.input.Input;
import org.example.item.Weapon;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class GameStateManager {

    private PlayerShip ship;
    private LinkedList<LazerBolt> lazerBolts;

    public GameStateManager(Input input) {
        this.lazerBolts = new LinkedList<LazerBolt>();
        this.ship = new PlayerShip(400, 400, new Weapon(), new HealthSystem(), input, lazerBolts);
    }

    public void update() {
        ship.update();
        Iterator<LazerBolt> iterator = lazerBolts.iterator();
        while (iterator.hasNext()) {
            LazerBolt lazer = iterator.next();
            lazer.update();

            if (lazer.isOutOfBounds())
                iterator.remove();
        }
    }

    public void render(Graphics g) {
        ship.render(g);
        Iterator<LazerBolt> iterator = lazerBolts.iterator();
        while (iterator.hasNext()) {
            LazerBolt lazer = iterator.next();
            lazer.render(g);
        }

    }
}