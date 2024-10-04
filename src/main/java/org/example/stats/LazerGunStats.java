package org.example.stats;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class LazerGunStats extends WeaponStats {
    private int critDame, critRate;
}