package org.example.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import org.example.assets.AssetManager;
import org.example.stats.BulletStats;
import org.example.util.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class Bullet extends Entity<BulletStats> { 
    private final BufferedImage[][] bullets = AssetManager.getLazerBoltAssets();
    private int direct;
    private final Timer bulletAnimationTimer = new Timer(100, e -> {
        this.direct = this.direct != 0 ? 0 : 1;
    });

    @Override
    public void render(Graphics g) {
        g.drawImage(
                this.bullets[0][this.direct].getScaledInstance(50, 50, Image.SCALE_DEFAULT),
                this.getPosition().getX(),
                this.getPosition().getY(),
                null
        );
    }

    @Override
    public void update(float deltaTime) {
        this.position.setY(this.position.getY() - 5);
        // this.position.setX(this.position.getX() + this.direct == 1 ? -5 : 5);
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
}