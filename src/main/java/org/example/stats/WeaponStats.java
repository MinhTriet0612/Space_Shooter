package org.example.stats;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class WeaponStats extends ItemStats {
  private int power = 10;
  private int mass = 20;
  private int ammunition = 30;
}
