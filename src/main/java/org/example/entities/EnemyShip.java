package org.example.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import org.example.assets.AssetManager;
import org.example.shape.Rectangle;
import org.example.stats.EnemyShipStats;
import org.example.stats.ShipStats;
import org.example.system.status.Status;
import org.example.util.Response;
import org.example.util.Vector2D;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class EnemyShip extends MortalEntity<EnemyShipStats> {
    private BufferedImage[][] sprites;
    private int isBoosting = 0;
    private int direction = 2;
    private int speed = 6;
    private Timer boostTimer;
    private Timer limitFireRate;
    private int fireRate = 150;
    private boolean canFire = true;

    // public EnemyShip(ShipStats stats) {
    //     this.status = new Status<>(stats);
    //     this.position = new Vector2D(300, 0);
    //     this.rigid = new Rectangle(50, 80);
    //     this.boostTimer = new Timer(130, e -> updateShipBoost());
    //     this.sprites = AssetManager.getShipAssets();
    //     this.boostTimer.start();
    // }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.sprites[this.isBoosting][this.direction].getScaledInstance(
            50, 100, Image.SCALE_DEFAULT), this.position.getX(), this.position.getY(), null);
    }

    @Override
    public void update(float deltaTime) {
        this.position.setY(this.position.getY() + 1);
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

    public void moveUp() {
        if (this.getPosition().getY() > 2) {
            this.getPosition().setY(this.getPosition().getY() - this.speed);
        }
    }

    public void moveDown() {
        if (this.getPosition().getY() < 600 - 50) {
            this.getPosition().setY(this.getPosition().getY() + this.speed);
        }
    }

    public void moveLeft() {
        if (this.getPosition().getX() > 0) {
            this.getPosition().setX(this.getPosition().getX() - this.speed);
        }
        if (this.direction > 0) {
            this.direction -= 1;
        }
    }

    public void moveRight() {
        if (this.getPosition().getX() < 800 - 126) {
            this.getPosition().setX(this.getPosition().getX() + this.speed);
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
    
}