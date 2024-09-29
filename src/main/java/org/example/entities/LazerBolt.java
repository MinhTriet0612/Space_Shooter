package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.assets.AssetManager;
import org.example.game.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
public class LazerBolt {
    private int x;
    private int y;
    private int width = 16;
    private int height = 24;
    private Timer lazerAnimationTimer;
    private BufferedImage[][] lazers;
    private BufferedImage currentLazer;

    public LazerBolt(int x, int y) {
        this.lazers = AssetManager.getLazerBoltAssets();
        currentLazer = lazers[1][0];

        this.x = x;
        this.y = y;

        lazerAnimationTimer = new Timer(100, e -> {
            if (currentLazer == lazers[1][0]) {
                currentLazer = lazers[1][1];
            } else {
                currentLazer = lazers[1][0];
            }
        });

        lazerAnimationTimer.start();
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
        g.drawImage(this.currentLazer.getScaledInstance(50, 50, Image.SCALE_DEFAULT), x, y, null);
    }

}
