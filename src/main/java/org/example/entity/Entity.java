package org.example.entity;

import java.awt.Color;
import java.awt.Graphics;

import org.example.constant.ScreenAttributeConstant;
import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.EntityStats;
import org.example.system.status.Status;
import org.example.util.GraphicsUtils;
import org.example.util.Response;
import org.example.util.Vector2D;
import org.example.util.GraphicsUtils.DrawMode;
import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Entity<S extends EntityStats> extends BaseObject {
  private World world;
  private Status<S> status;
  private Vector2D position = new Vector2D(0, 0);
  private Vector2D velocity = new Vector2D(0, 0);
  private boolean markAsRemoved = false;
  private boolean isCollidable = true;
  private boolean isVisible = true;
  private boolean debugRigid = true;

  public Entity() {
  }

  public abstract Rigid getRigid();

  public void render(Graphics g) {
  }

  public void afterRender(Graphics g) {
    if (this.debugRigid) {
      Circle circle = (Circle) this.getRigid();
      int x = (int) circle.getPosition().getX();
      int y = (int) circle.getPosition().getY();
      int radius = circle.getRadius();
      GraphicsUtils.drawEllipse(g, x, y, radius * 2, radius * 2, Color.WHITE, DrawMode.CENTER);
    }
  }

  public void update(float deltaTime) {
    // this.position.add(this.velocity);
    int tmp = 800;
    if (this.position.getY() >= tmp
        || this.position.getY() <= -tmp || this.getPosition().getX() <= 0 || this.position.getX() >= tmp)
      this.getWorld().getEntities().remove(this);
    // this.velocity.scale(this.getStatus().getCurrentStats().getFriction());
  }

  public void onAdd() {
  }

  public void onRemove() {
    this.world.getEntities().remove(this);
  }

  public void onCollisionStay(Entity<?> entity2, Response response) {
  }

  public void onCollisionExit(Entity<?> other, Response response) {
  }

  public void onCollisionEnter(Entity<?> other, Response response) {
  }

  public S getCurrentStats() {
    return this.getStatus().getCurrentStats();
  }

  public S getInitStats() {
    return this.getStatus().getInitStats();
  }

  public void destroy() {
    this.markAsRemoved = true;
  }

  public void setPosition(int x, int y) {
    this.position.setX(x);
    this.position.setY(y);
  }

  public void applyForce(Vector2D force) {
    this.velocity.add(force);
  }
}
