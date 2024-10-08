package org.example.stats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterStats extends MortalEntityStats {
  private int damage = 10;
  private int size = 20;
}
