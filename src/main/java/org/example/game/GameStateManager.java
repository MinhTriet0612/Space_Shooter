package org.example.game;

import org.example.entities.LazerBolt;
import org.example.entities.Ship;
import org.example.entities.Space;
import org.example.input.Input;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

    private Ship ship;
    private ArrayList<LazerBolt> lazerBolts;

    public GameStateManager(Input input)
    {
        this.lazerBolts = new ArrayList<LazerBolt>();
        this.ship = new Ship(0,0, input, lazerBolts );
    }

    public void update()
    {
        ship.update();
        for (LazerBolt lazerBolt : lazerBolts)
        {
            lazerBolt.update();
        }
    }

    public void render(Graphics g)
    {
        ship.render(g);
        for (LazerBolt lazerBolt : lazerBolts)
        {
            lazerBolt.render(g);
        }
    }
}
