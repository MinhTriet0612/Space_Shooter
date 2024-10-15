package org.example.stats;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LazergunStats extends WeaponStats {
  private int critDame = 2;
  private int critRate = 10;
  private float friction = 0.9f;
  private int ammunition = 50;
  private int fireRate = 6;
}
