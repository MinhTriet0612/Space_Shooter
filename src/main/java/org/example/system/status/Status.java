package org.example.system.status;

import java.util.ArrayList;
import java.util.List;

import org.example.stats.Stats;
import org.example.util.DeepCopyUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Status<T extends Stats> {
  private T initStats;
  private T currentStats;
  private List<Effect> effects = new ArrayList<>();

  public Status(T stats) {
    this.initStats = DeepCopyUtils.copy(stats);
    this.currentStats = DeepCopyUtils.copy(stats);
  }
}
