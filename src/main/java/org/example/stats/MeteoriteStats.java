package org.example.stats;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class MeteoriteStats extends EntityStats {
  private final int critRate = 100;
  private int critDame;
}
