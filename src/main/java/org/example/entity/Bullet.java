package org.example.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.BulletStats;
import org.example.system.status.Status;
import org.example.util.AssetManager;
import org.example.util.Response;
import org.example.util.Vector2D;
import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bullet extends Entity<BulletStats> {
  private Status<BulletStats> status = new Status<>(new BulletStats());

  private int direct;
  private final BufferedImage[][] bullets = AssetManager.getLazerBoltAssets();
  private final Timer bulletAnimationTimer = new Timer(150, e -> this.direct = this.direct != 0 ? 0 : 1);

  public Bullet(World world, Vector2D position, Vector2D velocity, boolean markAsRemoved, boolean isCollidable,
      boolean isVisible, Status<BulletStats> status, int direct) {
    super(world, position, velocity, markAsRemoved, isCollidable, isVisible);
    this.status = status;
    this.direct = direct;
  }

  public Rigid getRigid() {
    return new Circle(this.getPosition(), this.status.getInitStats().getRadius());
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(
        this.bullets[0][this.direct].getScaledInstance(50, 50, Image.SCALE_DEFAULT),
        (int) this.getPosition().getX(),
        (int) this.getPosition().getY(),
        null);
  }

  @Override
  public void update(float deltaTime) {
    this.getPosition().setY(this.getPosition().getY() - 5);
    // this.position.setX(this.position.getX() + this.direct == 1 ? -5 : 5);
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

  public static Bullet.BulletBuilder builder() {
    return new Bullet.BulletBuilder();
  }

  public static class BulletBuilder {
    private World world;
    private Vector2D position;
    private Vector2D velocity;
    private boolean markAsRemoved;
    private boolean isCollidable;
    private boolean isVisible;
    private Status<BulletStats> status;
    private int direct;

    public BulletBuilder world(World world) {
      this.world = world;
      return this;
    }

    public BulletBuilder position(Vector2D position) {
      this.position = position;
      return this;
    }

    public BulletBuilder velocity(Vector2D velocity) {
      this.velocity = velocity;
      return this;
    }

    public BulletBuilder markAsRemoved(boolean markAsRemoved) {
      this.markAsRemoved = markAsRemoved;
      return this;
    }

    public BulletBuilder isCollidable(boolean isCollidable) {
      this.isCollidable = isCollidable;
      return this;
    }

    public BulletBuilder isVisible(boolean isVisible) {
      this.isVisible = isVisible;
      return this;
    }

    public BulletBuilder status(Status<BulletStats> status) {
      this.status = status;
      return this;
    }

    public BulletBuilder direct(int direct) {
      this.direct = direct;
      return this;
    }

    public Bullet build() {
      return new Bullet(world, position, velocity, markAsRemoved, isCollidable, isVisible, status, direct);
    }
  }

}
