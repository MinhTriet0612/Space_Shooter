package org.example.stats;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LazerGunStats extends WeaponStats {
  private int critDame = 2;
  private int critRate = 10;
}
