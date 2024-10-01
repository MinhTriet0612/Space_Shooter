package org.example.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnemyShipStatus extends ShipStatus {

    public EnemyShipStatus(int health, int speed) {
        super(health, speed);
    }
    
}