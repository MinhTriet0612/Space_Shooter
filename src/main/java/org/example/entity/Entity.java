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
  protected World world;
  protected Status<S> status;
  protected Vector2D position = new Vector2D(0, 0);
  protected boolean markAsRemoved = false;
  protected boolean isCollidable = true;
  protected boolean isVisible = true;

  public Entity() {
  }

  public abstract Rigid getRigid();

  public abstract void render(Graphics g);

  public abstract void update(float deltaTime);

  public abstract void onCollisionStay(Entity<?> entity2, Response response);

  public abstract void onCollisionExit(Entity<?> other, Response response);

  public abstract void onCollisionEnter(Entity<?> other, Response response);

  public void destroy() {
    this.markAsRemoved = true;
  }

  public void setPosition(int x, int y) {
    this.position.setX(x);
    this.position.setY(y);
  }
}
