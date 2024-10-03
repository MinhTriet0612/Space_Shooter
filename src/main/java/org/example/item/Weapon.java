package org.example.item;

import org.example.stats.WeaponStats;
import org.example.system.status.Status;

import lombok.Getter;
import lombok.Setter; 


public abstract class Weapon extends Item {
    @Setter
    @Getter
    protected boolean isFiring;

    protected Status<WeaponStats> status;

    public abstract void fire();
    public abstract boolean setAuto();
}