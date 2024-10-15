package org.example.rigid;

import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Rectangle extends Rigid {
  private int width, height;

  public Rectangle(int x, int y, int width, int height) {
    super(new Vector2D(x, y));
    this.width = width;
    this.height = height;
  }

  public Rectangle(Vector2D position, int width, int height) {
    super(position);
    this.width = width;
    this.height = height;
  }
}
