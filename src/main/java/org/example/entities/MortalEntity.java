package org.example.entities;

import org.example.shape.Rigid;
import org.example.status.MortalEntityStatus;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MortalEntity extends Entity<MortalEntityStatus> {

    public MortalEntity(MortalEntityStatus status, Rigid rigid, Vector2D position) {
        super(status, rigid, position);
    }

    public boolean isDead() {
        return this.status.getHealth() <= 0;
    }

    public void injure(int damage) {
        this.status.setHealth(this.status.getHealth() - damage);
    }

    public void 

}
