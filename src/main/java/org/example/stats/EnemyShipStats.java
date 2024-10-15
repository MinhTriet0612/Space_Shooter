package org.example.stats;

import java.util.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnemyShipStats extends ShipStats {
  private float friction = 1;

  // each move has possibility of 1/5
  private List<Integer> movementFactor = new ArrayList<>(Arrays.asList(1, 2, 4, 8, 16));
}
