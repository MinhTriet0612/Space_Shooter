package org.example.stats;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class MortalEntityStats extends EntityStats {
    protected int speed;
    protected int def;
}