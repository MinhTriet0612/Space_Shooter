package org.example.item;

import org.example.entities.Bullet;
import org.example.stats.WeaponStats;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public abstract class Weapon<T extends WeaponStats> extends Item<WeaponStats> { 
    protected boolean isFiring, isReload;

    public abstract Bullet fire(Vector2D position);
    public abstract boolean setAuto();
}
