package org.example.system.collision;

import org.example.entity.Entity;
import org.example.rigid.Circle;
import org.example.rigid.Rectangle;
import org.example.rigid.Rigid;
import org.example.stats.SystemStats;
import org.example.system.GameSystem;
import org.example.util.Response;
import org.example.util.Vector2D;
import java.util.HashMap;

public class CollisionSystem extends GameSystem<SystemStats> {
  private HashMap<String, Response> oldCollisionMap = new HashMap<>();
  private HashMap<String, Response> newCollisionMap = new HashMap<>();

  @Override
  public void update(float deltaTime) {
    this.newCollisionMap.clear();
    for (int i = 0; i < this.getWorld().getEntities().size(); i++) {
      for (int j = 0; j < this.getWorld().getEntities().size(); j++) {
        if (i == j) {
          continue;
        }
        Entity<?> entity1 = this.getWorld().getEntities().get(i);
        Entity<?> entity2 = this.getWorld().getEntities().get(j);

        if (entity1.isCollidable() && entity2.isCollidable()) {
          Rigid r1 = entity1.getRigid();
          Rigid r2 = entity2.getRigid();

          if (r1 == null || r2 == null) {
            continue;
          }

          Class<?> type1 = entity1.getRigid().getClass();
          Class<?> type2 = entity2.getRigid().getClass();

          if (type1 == Circle.class && type2 == Circle.class) {
            this.handleCircleCircle(entity1, entity2);
          } else if (type1 == Rectangle.class && type2 == Rectangle.class) {
            this.handleRectangleRectangle(entity1, entity2);
          } else if ((type1 == Circle.class && type2 == Rectangle.class) ||
              (type1 == Rectangle.class && type2 == Circle.class)) {
            this.handleCircleRectangle(entity1, entity2);
          }
        }
      }
    }

    for (String key : this.oldCollisionMap.keySet()) {
      if (!this.newCollisionMap.containsKey(key)) {
        Entity<?> entity1 = this.oldCollisionMap.get(key).getA();
        Entity<?> entity2 = this.oldCollisionMap.get(key).getB();
        entity1.onCollisionExit(entity2, this.oldCollisionMap.get(key));
        this.oldCollisionMap.remove(key);
      }
    }
  }

  private String getCollisionKey(Entity<?> entity1, Entity<?> entity2) {
    return entity1.getUuid().toString() + entity2.getUuid().toString();
  }

  private void handleCircleCircle(Entity<?> entity1, Entity<?> entity2) {
    Circle circle1 = (Circle) entity1.getRigid();
    Circle circle2 = (Circle) entity2.getRigid();
    double distance = circle1.getPosition().distance(circle2.getPosition());

    Vector2D overlapN = circle1.getPosition().subtract(circle2.getPosition()).normalize();
    Vector2D overlapV = overlapN.scale((int) (circle1.getRadius() + circle2.getRadius() - distance));

    boolean aInB = circle1.getPosition().distance(circle2.getPosition()) < circle2.getRadius()
        - circle1.getRadius();
    boolean bInA = circle1.getPosition().distance(circle2.getPosition()) < circle1.getRadius()
        - circle2.getRadius();
    Response response = new Response(entity1, entity2, overlapN, overlapV, aInB, bInA);

    if (distance < circle1.getRadius() + circle2.getRadius()) {
      // Collision detected

      String key = this.getCollisionKey(entity1, entity2);
      if (this.oldCollisionMap.containsKey(key)) {
        entity1.onCollisionStay(entity2, response);
      } else {
        entity1.onCollisionEnter(entity2, response);
      }

      this.newCollisionMap.put(key, response);
    }
  }

  private void handleCircleRectangle(Entity<?> entity1, Entity<?> entity2) {
    Circle circle = (Circle) entity1.getRigid();
    Rectangle rectangle = (Rectangle) entity2.getRigid();

    Vector2D closest = new Vector2D(
        Math.max(rectangle.getPosition().getX() - rectangle.getWidth() / 2,
            Math.min(circle.getPosition().getX(), rectangle.getPosition().getX() + rectangle.getWidth() / 2)),
        Math.max(rectangle.getPosition().getY() - rectangle.getHeight() / 2,
            Math.min(circle.getPosition().getY(), rectangle.getPosition().getY() + rectangle.getHeight() / 2)));

    boolean inside = circle.getPosition().equals(closest);
    Vector2D normal = circle.getPosition().subtract(closest);
    double distance = normal.magnitude();
    Vector2D overlapV = normal.scale((int) (circle.getRadius() - distance));

    boolean aInB = circle.getPosition().distance(rectangle.getPosition()) < rectangle.getWidth() / 2
        - circle.getRadius();
    boolean bInA = circle.getPosition().distance(rectangle.getPosition()) < circle.getRadius()
        - rectangle.getWidth() / 2;
    Response response = new Response(entity1, entity2, normal.normalize(), overlapV, aInB, bInA);

    if (circle.getPosition().distance(closest) < circle.getRadius() && !inside) {
      // Collision detected

      String key = this.getCollisionKey(entity1, entity2);
      if (this.oldCollisionMap.containsKey(key)) {
        entity1.onCollisionStay(entity2, response);
      } else {
        entity1.onCollisionEnter(entity2, response);
      }

      this.newCollisionMap.put(key, response);
    }
  }

  private void handleRectangleRectangle(Entity<?> entity1, Entity<?> entity2) {
    Rectangle rectangle1 = (Rectangle) entity1.getRigid();
    Rectangle rectangle2 = (Rectangle) entity2.getRigid();

    boolean aInB = rectangle1.getPosition().getX() - rectangle1.getWidth() / 2 < rectangle2.getPosition().getX()
        + rectangle2.getWidth() / 2
        && rectangle1.getPosition().getX() + rectangle1.getWidth() / 2 > rectangle2.getPosition().getX()
            - rectangle2.getWidth() / 2
        && rectangle1.getPosition().getY() - rectangle1.getHeight() / 2 < rectangle2.getPosition().getY()
            + rectangle2.getHeight() / 2
        && rectangle1.getPosition().getY() + rectangle1.getHeight() / 2 > rectangle2.getPosition().getY()
            - rectangle2.getHeight() / 2;

    boolean bInA = rectangle2.getPosition().getX() - rectangle2.getWidth() / 2 < rectangle1.getPosition().getX()
        + rectangle1.getWidth() / 2
        && rectangle2.getPosition().getX() + rectangle2.getWidth() / 2 > rectangle1.getPosition().getX()
            - rectangle1.getWidth() / 2
        && rectangle2.getPosition().getY() - rectangle2.getHeight() / 2 < rectangle1.getPosition().getY()
            + rectangle1.getHeight() / 2
        && rectangle2.getPosition().getY() + rectangle2.getHeight() / 2 > rectangle1.getPosition().getY()
            - rectangle1.getHeight() / 2;

    Vector2D overlapN = rectangle1.getPosition().subtract(rectangle2.getPosition()).normalize();
    Vector2D overlapV = overlapN.scale((int) (rectangle1.getWidth() / 2 + rectangle2.getWidth() / 2
        - Math.abs(rectangle1.getPosition().subtract(rectangle2.getPosition()).getX())));
    Response response = new Response(entity1, entity2, overlapN, overlapV, aInB, bInA);

    if (rectangle1.getPosition().getX() - rectangle1.getWidth() / 2 < rectangle2.getPosition().getX()
        + rectangle2.getWidth() / 2
        && rectangle1.getPosition().getX() + rectangle1.getWidth() / 2 > rectangle2.getPosition().getX()
            - rectangle2.getWidth() / 2
        && rectangle1.getPosition().getY() - rectangle1.getHeight() / 2 < rectangle2.getPosition().getY()
            + rectangle2.getHeight() / 2
        && rectangle1.getPosition().getY() + rectangle1.getHeight() / 2 > rectangle2.getPosition().getY()
            - rectangle2.getHeight() / 2) {
      // Collision detected

      String key = this.getCollisionKey(entity1, entity2);
      if (this.oldCollisionMap.containsKey(key)) {
        entity1.onCollisionStay(entity2, response);
      } else {
        entity1.onCollisionEnter(entity2, response);
      }

      this.newCollisionMap.put(key, response);
    }
  }
}
