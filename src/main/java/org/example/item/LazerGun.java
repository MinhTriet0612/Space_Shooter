package org.example.item;

import org.example.builder.BulletBuilder;
import org.example.entities.Bullet;
import org.example.entities.Entity;
import org.example.stats.LazerGunStats;
import org.example.util.Vector2D;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.swing.Timer;

@Getter
@Setter
@SuperBuilder
public class LazerGun extends Weapon<LazerGunStats> { 
    private final Timer reloadAmmunition = new Timer(1000, e -> this.reloadAmmunition());
    private final Timer reloadForNextBullet = new Timer(150, e -> this.reloadForNextBullet());

    @Override
    public Bullet fire(Vector2D position) {
        if (this.status.getCurrentStast().getAmmunition() <= 0) {
            this.isReload = true;
            this.status.getCurrentStast().setAmmunition(this.status.getInitStast().getAmmunition());
            this.reloadAmmunition.start();
        } else if(this.isFiring && !isReload) {
            this.status.getCurrentStast().setAmmunition(this.status.getCurrentStast().getAmmunition() - 1);
            this.isFiring = false;
            this.reloadForNextBullet.stop();
            return BulletBuilder.bulletBuilder(position);
        } else {
            this.reloadForNextBullet.start();
            this.reloadAmmunition.start();
        }
        return null;
    }

    public void reloadForNextBullet() {
        this.isFiring = true;
    }

    public void reloadAmmunition() {
        this.isReload = false;
    }

    @Override
    public boolean setAuto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAuto'");
    }

    @Override
    public void update(float deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}