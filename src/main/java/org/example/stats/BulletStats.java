package org.example.stats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BulletStats extends EntityStats {
  private int speed = 10;
  private int damage = 1;
  private int radius = 8;
}
