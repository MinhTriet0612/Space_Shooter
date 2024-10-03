package org.example.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LazerGunStats extends WeaponStats {
    private int critDame, critRate;
}