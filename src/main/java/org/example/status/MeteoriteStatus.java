package org.example.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MeteoriteStatus extends EntityStatus {
    private int speed, damage;
}
