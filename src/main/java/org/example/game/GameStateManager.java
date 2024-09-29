package org.example.game;

import lombok.Getter;
import org.example.entities.LazerBolt;
import org.example.entities.Ship;
import org.example.input.Input;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class GameStateManager {

    private Ship ship;
    @Getter
    private LinkedList<LazerBolt> lazerBolts;

    public GameStateManager(Input input)
    {
        this.lazerBolts = new LinkedList<LazerBolt>();
        this.ship = new Ship(0,0, input, lazerBolts );
    }

    public void update()
    {
        ship.update();
        Iterator<LazerBolt> iterator = lazerBolts.iterator();
        while(iterator.hasNext())
        {
            LazerBolt lazer = iterator.next();
            lazer.update();

            if(lazer.isOutOfBounds())
            {
                iterator.remove();
            }
        }
    }

    public void render(Graphics g)
    {
        ship.render(g);
        Iterator<LazerBolt> iterator = lazerBolts.iterator();
        while(iterator.hasNext())
        {
            LazerBolt lazer = iterator.next();
            lazer.render(g);
        }

    }
}
