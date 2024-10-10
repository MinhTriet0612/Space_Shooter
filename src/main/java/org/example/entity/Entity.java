package org.example.entity;

import java.awt.Color;
import java.awt.Graphics;

import org.example.BaseGameObject;
import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.EntityStats;
import org.example.util.GraphicsUtil;
import org.example.util.Response;
import org.example.util.Vector2D;
import org.example.util.GraphicsUtil.DrawMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Entity<S extends EntityStats> extends BaseGameObject<S> {
  private Vector2D position = new Vector2D(0, 0);
  private Vector2D velocity = new Vector2D(0, 0);
  private boolean markAsRemoved = false;
  private boolean isCollidable = true;
  private boolean isVisible = true;
  private boolean debugRigid = true;

  public Entity() {
  }

  public abstract Rigid getRigid();

  @Override
  public boolean isReady() {
    return super.isReady() && this.getRigid() != null;
  }

  @Override
  public void update(float deltaTime) {
    this.position.add(this.velocity);
    this.velocity.scale(this.getStatus().getCurrentStats().getFriction());
  }

  @Override
  public void afterRender(Graphics g) {
    if (this.debugRigid) {
      Circle circle = (Circle) this.getRigid();
      int x = (int) circle.getPosition().getX();
      int y = (int) circle.getPosition().getY();
      int radius = circle.getRadius();
      GraphicsUtils.drawEllipse(g, x, y, radius * 2, radius * 2, Color.WHITE, DrawMode.CENTER);
    }
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
