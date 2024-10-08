package org.example.entity;

import java.awt.Graphics;

import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.MeteoriteStats;
import org.example.system.status.Status;
import org.example.util.Response;
import org.example.util.Vector2D;
import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meteorite extends Entity<MeteoriteStats> {
  private Status<MeteoriteStats> status = new Status<>(new MeteoriteStats());

  protected Meteorite(World world, Vector2D position, Vector2D velocity, boolean markAsRemoved, boolean isCollidable,
      boolean isVisible, Status<MeteoriteStats> status) {
    super(world, position, velocity, markAsRemoved, isCollidable, isVisible);
    this.status = status;
  }

  public Rigid getRigid() {
    return new Circle(this.getPosition(), this.status.getInitStats().getRadius());
  }

  @Override
  public void render(Graphics g) {
    // throw new UnsupportedOperationException("Unimplemented method 'render'");
  }

  @Override
  public void update(float deltaTime) {
    // throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void onCollisionStay(Entity<?> other, Response response) {
    // throw new UnsupportedOperationException("Unimplemented method
    // 'onCollisionStay'");
  }

  @Override
  public void onCollisionExit(Entity<?> other, Response response) {
    // throw new UnsupportedOperationException("Unimplemented method
    // 'onCollisionExit'");
  }

  @Override
  public void onCollisionEnter(Entity<?> other, Response response) {
    // throw new UnsupportedOperationException("Unimplemented method
    // 'onCollisionEnter'");
  }

  public static Meteorite.MeteoriteBuilder builder() {
    return new Meteorite.MeteoriteBuilder();
  }

  public static class MeteoriteBuilder {
    private World world;
    private Vector2D position;
    private Vector2D velocity;
    private boolean markAsRemoved;
    private boolean isCollidable;
    private boolean isVisible;
    private Status<MeteoriteStats> status;

    public MeteoriteBuilder world(World world) {
      this.world = world;
      return this;
    }

    public MeteoriteBuilder position(Vector2D position) {
      this.position = position;
      return this;
    }

    public MeteoriteBuilder velocity(Vector2D velocity) {
      this.velocity = velocity;
      return this;
    }

    public MeteoriteBuilder markAsRemoved(boolean markAsRemoved) {
      this.markAsRemoved = markAsRemoved;
      return this;
    }

    public MeteoriteBuilder isCollidable(boolean isCollidable) {
      this.isCollidable = isCollidable;
      return this;
    }

    public MeteoriteBuilder isVisible(boolean isVisible) {
      this.isVisible = isVisible;
      return this;
    }

    public MeteoriteBuilder status(Status<MeteoriteStats> status) {
      this.status = status;
      return this;
    }

    public Meteorite build() {
      return new Meteorite(world, position, velocity, markAsRemoved, isCollidable, isVisible, status);
    }
  }

}
