package org.example.entity;

import java.awt.Graphics;

import org.example.rigid.Rigid;
import org.example.stats.EntityStats;
import org.example.system.status.Status;
import org.example.util.Response;
import org.example.util.Vector2D;
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

  public Entity() {
  }

  public abstract Rigid getRigid();

  public abstract void render(Graphics g);

  public void update(float deltaTime) {
    this.position.add(this.velocity);
    this.velocity.scale(this.status.getCurrentStats().getFriction());
  }

  public void onAdd() {
  }

  public void onRemove() {
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
