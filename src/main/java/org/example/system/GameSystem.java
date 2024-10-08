package org.example.system;

import org.example.entity.BaseObject;
import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GameSystem extends BaseObject {
  private World world;

  public abstract void update(float deltaTime);
}
