package org.example.entities;

import org.example.assets.AssetManager;
import org.example.input.Input;
import org.example.item.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class PlayerShip extends Ship {
    private BufferedImage[][] sprites;
    private Timer limitFireRate;
    private Timer boostTimer;

    private int isBoosting = 0;
    private int direction = 2;
    private int speed = 6;
    private Input input;  // Reference to the Input class
    private LinkedList<LazerBolt> lazerBolts;
    private int fireRate = 150;
    private boolean canFire = true;

    public PlayerShip(int x, int y, Weapon weapon, HealthSystem health, Input input, LinkedList<LazerBolt> lazerBolts) {
        super(x, y, weapon, health);  // Initialize position
        this.input = input;  // Reference to the input handler
        this.sprites = AssetManager.getShipAssets();
        this.lazerBolts = lazerBolts;

        this.boostTimer = new Timer(130, e -> updateShipBoost());
        this.boostTimer.start();

        this.limitFireRate = new Timer(this.fireRate, e -> {
            this.canFire = true;
            this.limitFireRate.stop();
        });
    }

    public void update() {
        // Read input from the Input class and move the ship accordingly
        if (input.up) {
            moveUp();
        } else if (input.down) {
            moveDown();
        }

        if (input.left) {
            moveLeft();
        } else if (input.right) {
            moveRight();
        } else {
            reRenderDirection();
        }

        if (input.space) {
            fireLazerBolt();
            input.space = false;
        }

    }

    @Override
    public void render(Graphics g) {
        // Render the ship based on current position and state
        g.drawImage(this.sprites[isBoosting][direction].getScaledInstance(
                50, 60, Image.SCALE_DEFAULT), this.x, this.y, null);
    }

    private void moveUp() {
        if (this.y > 2) {
            this.y -= this.speed;
        }
    }

    private void moveDown() {
        if (this.y < 600 - 50) {
            this.y += this.speed;
        }
    }

    private void moveLeft() {
        if (this.x > 0) {
            this.x -= this.speed;
        }
        if (this.direction > 0) {
            this.direction -= 1;
        }
    }

    private void moveRight() {
        if (this.x < 800 - 126) {
            this.x += this.speed;
        }
        if (this.direction < 4) {
            this.direction += 1;
        }
    }

    private void reRenderDirection() {
        this.direction = 2;  // Reset direction to neutral (center) when no left/right is pressed
    }

    private void updateShipBoost() {
        this.isBoosting = (this.isBoosting == 0) ? 1 : 0;
    }

    private void fireLazerBolt() {
        if (!this.canFire) {
            return;
        }
        LazerBolt lazerBolt = new LazerBolt(this.x - 2, this.y - 30);
        this.lazerBolts.addLast(lazerBolt);
        this.canFire = false;
        this.limitFireRate.start();
    }

}