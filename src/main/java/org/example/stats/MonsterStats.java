package org.example.stats;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MonsterStats extends MortalEntityStats {
    private final int damage = this.health / 20;
}