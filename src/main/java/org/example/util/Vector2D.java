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

  public static Vector2D fromAngle(double angle) {
    return new Vector2D((float) Math.cos(angle), (float) Math.sin(angle));
  }

  public double angle() {
    return Math.atan2(y, x);
  }

  public Vector2D add(Vector2D other) {
    this.x += other.getX();
    this.y += other.getY();
    return this;
  }

  public Vector2D mult(Vector2D other) {
    this.x *= other.getX();
    this.y *= other.getY();
    return this;
  }

  public Vector2D mult(float factor) {
    this.x *= factor;
    this.y *= factor;
    return this;
  }

  public Vector2D subtract(Vector2D other) {
    return new Vector2D(x - other.getX(), y - other.getY());
  }

  public Vector2D scale(float factor) {
    return new Vector2D(x * factor, y * factor);
  }

  public Vector2D clone() {
    return new Vector2D(x, y);
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

  public double distance(Vector2D other) {
    return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
  }

  public Vector2D rotate(double angle) {
    double cos = Math.cos(angle);
    double sin = Math.sin(angle);
    return new Vector2D((int) (x * cos - y * sin), (int) (x * sin + y * cos));
  }

  public void setPosition(Vector2D position) {
    this.x = position.getX();
    this.y = position.getY();
  }

  public String toString() {
    return "Vector2D(" + x + ", " + y + ")";
  }
}
