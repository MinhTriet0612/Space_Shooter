package org.example.entities;

import org.example.assets.AssetManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Space {
    private BufferedImage backgroundImage;


    public Space() {
        this.backgroundImage = AssetManager.getBackground();
    }

    public void draw(Graphics g) {
        g.drawImage(this.backgroundImage, 0, 0, 800, 600, null);
    }
}