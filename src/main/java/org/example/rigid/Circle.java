package org.example.rigid;

import lombok.Getter;
import lombok.Setter;

import org.example.util.Vector2D;

@Setter
@Getter
public class Circle extends Rigid {
  private int radius;

  public Circle(Vector2D position, int radius) {
    super(position);
    this.radius = radius;
  }
}
