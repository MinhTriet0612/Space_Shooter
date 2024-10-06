package org.example.util;

import org.example.entities.BaseObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Vector2D extends BaseObject {
    private int x, y;

    public double angle() {
        return Math.atan2(y, x);
    }

    public double angleInDegrees() {
        return Math.toRadians(angle());
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D normalize() {
        double mag = magnitude();
        return new Vector2D((int) (x / mag), (int) (y / mag));
    }

    public Vector2D scale(int factor) {
        return new Vector2D(x * factor, y * factor);
    }
}