package org.example.stats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MortalEntityStats extends EntityStats {
    protected int speed;
    protected int def;
}