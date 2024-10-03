package org.example.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import org.example.assets.AssetManager;
import org.example.stats.ShipStats;
import org.example.system.status.Status;
import org.example.util.Response;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Ship extends MortalEntity<ShipStats> {

    private final BufferedImage[][] sprites = AssetManager.getShipAssets();
    private int isBoosting;
    private int direction;
    private final Timer boostTimer = new Timer(130, e -> this.updateShipBoost());
    private Timer limitFireRate;
    private int fireRate;
    private int speed;
    private boolean canFire;

    // public Ship(ShipStats stats) {
    //     this.status = new Status<>(stats);
    //     this.position = new Vector2D(384, 514);
    //     this.rigid = new Rectangle(50, 80);
    //     this.boostTimer = new Timer(130, e -> updateShipBoost());
    //     this.sprites = AssetManager.getShipAssets();
    //     this.boostTimer.start();
    // }
    @Override
    public void render(Graphics g) {
        this.status = new Status<>(null);
        g.drawImage(this.sprites[this.isBoosting][this.direction].getScaledInstance(
                50, 100, Image.SCALE_DEFAULT), this.position.getX(), this.position.getY(), null);
    }

    @Override
    public void update(float deltaTime) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void onCollisionStay(Entity other, Response response) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCollisionStay'");
    }

    @Override
    public void onCollisionExit(Entity other, Response response) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCollisionExit'");
    }

    @Override
    public void onCollisionEnter(Entity other, Response response) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onCollisionEnter'");
    }

    public void startTimer() {
        this.boostTimer.start();
    }

    public void moveUp() {
        if (this.position.getY() > 2) {
            this.position.setY(this.position.getY() - this.speed);
        }
    }

    public void moveDown() {
        if (this.position.getY() < 600 - 50) {
            this.position.setY(this.position.getY() + this.speed);
        }
    }

    public void moveLeft() {
        if (this.position.getX() > 0) {
            this.position.setX(this.position.getX() - this.speed);
        }
        if (this.direction > 0) {
            this.direction -= 1;
        }
    }

    public void moveRight() {
        if (this.position.getX() < 800 - 126) {
            this.position.setX(this.position.getX() + this.speed);
        }
        if (this.direction < 4) {
            this.direction += 1;
        }
    }

    public void reRenderDirection() {
        this.direction = 2;  // Reset direction to neutral (center) when no left/right is pressed
    }

    public void updateShipBoost() {
        this.isBoosting = (this.isBoosting == 0) ? 1 : 0;
    }

    // public Integer getIsBoosting() {
    //     return isBoosting;
    // }
    // public Integer getDirection() {
    //     return direction;
    // }
    // public void setIsBoosting(Integer isBoosting) {
    //     this.isBoosting = isBoosting;
    // }
    // public void setDirection(Integer direction) {
    //     this.direction = direction;
    // }
}
