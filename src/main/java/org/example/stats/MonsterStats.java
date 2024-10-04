package org.example.stats;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class MonsterStats extends MortalEntityStats {
    private final int damage = this.health / 20;
}