package org.example.stats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterStats extends MortalEntityStats {
    private final int damage = this.health / 20;
}