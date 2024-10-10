package org.example.util;

import org.example.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Vector2D extends BaseObject {
  private float x, y;

  public double angle() {
    return Math.atan2(y, x);
  }

  public Vector2D subtract(Vector2D other) {
    return new Vector2D(this.x - other.x, this.y - other.y);
  }

  public double magnitude() {
    return Math.sqrt(x * x + y * y);
  }

  public static Vector2D directionTo(Vector2D source, Vector2D target) {
    float dx = target.x - source.getX();
    float dy = target.y - source.getY();
    float length = (float) Math.sqrt(dx * dx + dy * dy);
    return new Vector2D(dx / length, dy / length);
}

  public Vector2D normalize() {
    double mag = magnitude();
    return new Vector2D((int) (x / mag), (int) (y / mag));
  }

  public Vector2D scale(float factor) {
    return new Vector2D(x * factor, y * factor);
  }

  public Vector2D multiply(Vector2D other) {
    return new Vector2D(x * other.x, y * other.y);
  }

  public double distance(Vector2D other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }

  public Vector2D add(Vector2D other) {
    return new Vector2D(this.x + other.x, this.y + other.y);
  }

  public void plus(Vector2D other) {
    this.x += other.getX();
    this.y += other.getY();
  }

  public Vector2D rotate(double angle) {
    double cos = Math.cos(angle);
    double sin = Math.sin(angle);
    return new Vector2D((int) (x * cos - y * sin), (int) (x * sin + y * cos));
  }
}
