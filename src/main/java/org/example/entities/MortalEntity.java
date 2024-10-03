package org.example.entities;

import org.example.item.Weapon;
import org.example.stats.MortalEntityStats;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class MortalEntity<S extends MortalEntityStats> extends Entity<S> {
    @Setter
    @Getter
    protected Weapon weapon;
    
    public boolean isDead() {
        return this.status.getCurrentStast().getHealth() <= 0;
    }

    public void injure(int damage) {
        this.status.getCurrentStast()
            .setHealth(
                this.status.getCurrentStast().getHealth() - (damage - this.status.getCurrentStast().getDef())
            );
    }

    public void heal(int hp) {
        this.status.getCurrentStast()
            .setHealth(
                Math.min(
                    this.status.getCurrentStast().getHealth() + hp,
                    this.status.getInitStast().getHealth()
                )
            );
    }
}