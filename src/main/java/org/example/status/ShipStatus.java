package org.example.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipStatus extends MortalEntityStatus {
    private int speed;

    public ShipStatus(int health, int speed) {
        super(health);
        this.speed = speed;
    }
}