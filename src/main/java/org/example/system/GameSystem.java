package org.example.system;

import org.example.BaseGameObject;
import org.example.stats.SystemStats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GameSystem<S extends SystemStats> extends BaseGameObject<S> {
}
