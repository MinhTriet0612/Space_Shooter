package org.example.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.Timer;

import org.example.assets.AssetManager;
import org.example.stats.MonsterStats;
import org.example.util.DeepCopyUtils;
import org.example.util.Response;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Monster extends MortalEntity<MonsterStats> {
    private int speed;
    private int direction;
    private int isBoosting;
    private int movePoint;
    private final BufferedImage[][] sprites = AssetManager.getEnemyAssets();
    private final Timer boostTimer = new Timer(130, e -> this.updateShipBoost()); 
    private final Timer movePointTimer = new Timer(1000, e -> this.updatedMovePoint());
    private final List<Integer> 
        verRange = List.of(1, 2, 3, 4, 0, 1, 2, 3, 4, 0), // doc
        horRange = List.of(1, 2, 3, 4, 0, -1, -2, -3, -4, -0); // ngang

    @Override
    public void render(Graphics g) {
        g.drawImage(this.sprites[isBoosting][direction].getScaledInstance(
                30, 55, Image.SCALE_AREA_AVERAGING), this.position.getX(), this.position.getY(), null);
    }

    @Override
    public void useWeapon() {
        Bullet bullet = this.weapon.fire(DeepCopyUtils.copy(this.position));
        if (bullet != null) this.world.getEntities().listIterator().add(bullet);
    } 

    @Override
    public void update(float deltaTime) {
        this.position.setY(this.position.getY() + this.verRange.get(this.movePoint));
        this.position.setX(this.position.getX() + this.horRange.get(this.movePoint));
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
        this.movePointTimer.start();
    }

    public void updatedMovePoint() {
        this.movePoint = (int) Math.round(Math.random() * (this.verRange.size() - 1));
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
        if (this.position.getX() < 600 - 126) {
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