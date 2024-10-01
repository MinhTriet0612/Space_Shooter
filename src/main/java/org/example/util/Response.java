package org.example.util;

import org.example.entities.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Entity obj1, obj2;
    private Vector2D overLapN, overLapV;
    private Boolean alnB, blnA;

    // private void calculate() {
    //     Vector2D relativePosition = this.obj1.getPosition().subtract(this.obj2.getPosition());
    //     double distance = relativePosition.magnitude();
        
    //     double totalRadius = a.radius + b.radius;
    //     double overlapMagnitude = totalRadius - distance;

    //     if (overlapMagnitude > 0) {
    //         // Calculate overlap normal (direction of separation)
    //         overlapN = relativePosition.normalize();

    //         // Calculate overlap vector (how much they overlap)
    //         overlapV = overlapN.scale(overlapMagnitude);

    //         // Check if one entity is completely inside the other
    //         aInB = (a.radius < b.radius) && (distance + a.radius <= b.radius);
    //         bInA = (b.radius < a.radius) && (distance + b.radius <= a.radius);
    //     } else {
    //         // No collision detected
    //         overlapN = new Vec2(0, 0);
    //         overlapV = new Vec2(0, 0);
    //         aInB = false;
    //         bInA = false;
    //     }
    // }
}