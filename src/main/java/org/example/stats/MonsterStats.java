package org.example.stats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonsterStats extends MortalEntityStats {
  protected int damage = this.health / 20;
  protected int size = 20;
}
