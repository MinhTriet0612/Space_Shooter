package org.example.util;

import org.example.rigid.Circle;
import org.example.rigid.Rectangle;
import org.example.rigid.Rigid;

public class SAT {
  public static Response<Rigid> testCollide(Rigid a, Rigid b) {
    if (a instanceof Circle && b instanceof Circle) {
      return testCircleCircle((Circle) a, (Circle) b);
    } else if (a instanceof Circle && b instanceof Rectangle) {
      return testCircleRectangle((Circle) a, (Rectangle) b);
    } else if (a instanceof Rectangle && b instanceof Circle) {
      return testCircleRectangle((Circle) b, (Rectangle) a);
    } else if (a instanceof Rectangle && b instanceof Rectangle) {
      return testRectangleRectangle((Rectangle) a, (Rectangle) b);
    }
    return null;
  }

  public static Response<Rigid> testCircleCircle(Circle circle1, Circle circle2) {
    double distance = circle1.getPosition().distance(circle2.getPosition());

    Vector2D overlapN = circle1.getPosition().subtract(circle2.getPosition()).normalize();
    Vector2D overlapV = overlapN.scale((int) (circle1.getRadius() + circle2.getRadius() - distance));

    boolean aInB = circle1.getPosition().distance(circle2.getPosition()) < circle2.getRadius()
        - circle1.getRadius();
    boolean bInA = circle1.getPosition().distance(circle2.getPosition()) < circle1.getRadius()
        - circle2.getRadius();

    boolean isColliding = distance < circle1.getRadius() + circle2.getRadius();
    return new Response<Rigid>(circle1, circle2, overlapN, overlapV, aInB, bInA, isColliding);
  }

  public static Response<Rigid> testCircleRectangle(Circle circle, Rectangle rectangle) {
    // Rectangle anchor point is at the top left corner

    Vector2D closest = new Vector2D(
        Math.max(rectangle.getPosition().getX(),
            Math.min(circle.getPosition().getX(), rectangle.getPosition().getX() + rectangle.getWidth())),
        Math.max(rectangle.getPosition().getY(),
            Math.min(circle.getPosition().getY(), rectangle.getPosition().getY() + rectangle.getHeight())));

    // boolean inside = circle.getPosition().equals(closest);
    Vector2D normal = circle.getPosition().subtract(closest);
    double distance = normal.magnitude();
    Vector2D overlapV = normal.scale((int) (circle.getRadius() - distance));

    boolean aInB = circle.getPosition().distance(rectangle.getPosition()) < rectangle.getWidth() / 2
        - circle.getRadius();
    boolean bInA = circle.getPosition().distance(rectangle.getPosition()) < circle.getRadius()
        - rectangle.getWidth() / 2;

    boolean isColliding = circle.getPosition().distance(closest) < circle.getRadius();
    return new Response<Rigid>(circle, rectangle, normal.normalize(), overlapV, aInB, bInA, isColliding);
  }

  public static Response<Rigid> testRectangleRectangle(Rectangle rectangle1, Rectangle rectangle2) {
    // Rectangle anchor point is at the top left corner

    boolean aInB = rectangle1.getPosition().getX() < rectangle2.getPosition().getX() + rectangle2.getWidth()
        && rectangle1.getPosition().getX() + rectangle1.getWidth() > rectangle2.getPosition().getX()
        && rectangle1.getPosition().getY() < rectangle2.getPosition().getY() + rectangle2.getHeight()
        && rectangle1.getPosition().getY() + rectangle1.getHeight() > rectangle2.getPosition().getY();

    boolean bInA = rectangle2.getPosition().getX() < rectangle1.getPosition().getX() + rectangle1.getWidth()
        && rectangle2.getPosition().getX() + rectangle2.getWidth() > rectangle1.getPosition().getX()
        && rectangle2.getPosition().getY() < rectangle1.getPosition().getY() + rectangle1.getHeight()
        && rectangle2.getPosition().getY() + rectangle2.getHeight() > rectangle1.getPosition().getY();

    return new Response<Rigid>(rectangle1, rectangle2, null, null, aInB, bInA, aInB || bInA);
  }
}
