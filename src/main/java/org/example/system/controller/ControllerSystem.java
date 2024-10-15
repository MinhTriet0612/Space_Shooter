package org.example.system.controller;

import java.util.Map;

import org.example.stats.SystemStats;
import org.example.system.GameSystem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ControllerSystem extends GameSystem<SystemStats> {
  private Map<String, Boolean> state;
}
