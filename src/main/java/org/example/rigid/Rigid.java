package org.example.rigid;

import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Rigid {
  protected Vector2D position;

  public Rigid(Vector2D position) {
    this.position = position;
  }
}
