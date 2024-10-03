package org.example.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BulletStats extends EntityStats { 
    private int speed, damage;
}
