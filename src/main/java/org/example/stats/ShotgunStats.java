package org.example.stats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShotgunStats extends WeaponStats {
  private int critDame = 2;
  private int critRate = 10;
  private float friction = 0.9f;
  private int ammunition = 12;
  private int fireRate = 6;
}
