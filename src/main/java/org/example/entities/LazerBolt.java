package org.example.entities;

import lombok.Getter;
import org.example.assets.AssetManager;
import org.example.game.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

@Getter
public class LazerBolt {
    private int x;
    private int y;
    private int width = 16;
    private int height = 24;
    private Timer timer;
    private LinkedList<LazerBolt> lazerBolts;

    private BufferedImage image;
    private BufferedImage[][] lazers = new BufferedImage[2][2];

    public LazerBolt(int x, int y) {
        lazers = AssetManager.getLazerBoltAssets();

        this.x = x;
        this.y = y;
    }


    public void update() {
        move();
        // auto destroy lazer bolt if it goes out of bounds
    }


    public void move() {
        y -= 10;
    }

    public boolean isOutOfBounds() {
        return y < -10;
    }

    public void render(Graphics g) {
        g.drawImage(this.lazers[1][1].getScaledInstance(50, 50, Image.SCALE_DEFAULT), x, y, null);
    }

}
