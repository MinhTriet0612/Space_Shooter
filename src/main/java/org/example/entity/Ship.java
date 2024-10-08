package org.example.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import org.example.item.Weapon;
import org.example.rigid.Circle;
import org.example.rigid.Rectangle;
import org.example.rigid.Rigid;
import org.example.stats.ShipStats;
import org.example.system.status.Status;
import org.example.util.AssetManager;
import org.example.util.DeepCopyUtils;
import org.example.util.Response;
import org.example.util.Vector2D;
import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship<S extends ShipStats> extends MortalEntity<S> {

  private Status<S> status = new Status<S>((S) new ShipStats());
  private int direction;
  private int isBoosting;
  private final BufferedImage[][] sprites = AssetManager.getShipAssets();
  private final Timer boostTimer = new Timer(130, e -> {
    this.updateShipBoost();
  });

  protected Ship(World world, Vector2D position, Vector2D velocity, boolean markAsRemoved, boolean isCollidable,
      boolean isVisible, Weapon<?> weapon, Status<S> status, int direction, int isBoosting) {
    super(world, position, velocity, markAsRemoved, isCollidable, isVisible, weapon);
    this.status = status;
    this.direction = direction;
    this.isBoosting = isBoosting;applyForce(velocity);
    this.boostTimer.start();
  }

  public Rigid getRigid() {
    return new Circle(getPosition(), this.getStatus().getInitStats().getSize());
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(this.sprites[this.isBoosting][this.direction].getScaledInstance(
        40, 70, Image.SCALE_DEFAULT), (int) this.getPosition().getX(), (int) this.getPosition().getY(), null);
  }

  @Override
  public void useWeapon() {
    Bullet bullet = this.getWeapon().fire(DeepCopyUtils.copy(this.getPosition()));
    if (bullet != null)
      this.getWorld().addEntity(bullet);
  }

  @Override
  public void update(float deltaTime) {
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

  public void startTimer() {
    this.boostTimer.start();
  }

  public void moveUp() {
    if (this.getPosition().getY() > 2) {
      this.getPosition().setY(this.getPosition().getY() - this.getCurrentStats().getSpeed());
    }
  }

  public void moveDown() {
    if (this.getPosition().getY() < 700) {
      this.getPosition().setY(this.getPosition().getY() + this.getCurrentStats().getSpeed());
    }
  }

  public void moveLeft() {
    if (this.getPosition().getX() > 0) {
      this.getPosition().setX(this.getPosition().getX() - this.getCurrentStats().getSpeed());
    }
    if (this.direction > 0) {
      this.direction -= 1;
    }
  }

  public void moveRight() {
    if (this.getPosition().getX() < 800) {
      // System.out.println(this.status.getInitStats());
      this.getPosition().setX(this.getPosition().getX() + this.getCurrentStats().getSpeed());
    }
    if (this.direction < 4) {
      this.direction += 1;
    }
  }

  public void reRenderDirection() {
    this.direction = 2; // Reset direction to neutral (center) when no left/right is pressed
  }

  public void updateShipBoost() {
    this.isBoosting = (this.isBoosting == 0) ? 1 : 0;
  }

  public static Ship.ShipBuilder<?> builder() {
    return new Ship.ShipBuilder<>();
  }

  public static class ShipBuilder<S extends ShipStats> {
    private World world;
    private Vector2D position;
    private Vector2D velocity;
    private boolean markAsRemoved;
    private boolean isCollidable;
    private boolean isVisible;
    private Weapon<?> weapon;
    private Status<S> status;
    private int direction;
    private int isBoosting;

    public ShipBuilder<S> world(World world) {
      this.world = world;
      return this;
    }

    public ShipBuilder<S> position(Vector2D position) {
      this.position = position;
      return this;
    }

    public ShipBuilder<S> velocity(Vector2D velocity) {
      this.velocity = velocity;
      return this;
    }

    public ShipBuilder<S> markAsRemoved(boolean markAsRemoved) {
      this.markAsRemoved = markAsRemoved;
      return this;
    }

    public ShipBuilder<S> isCollidable(boolean isCollidable) {
      this.isCollidable = isCollidable;
      return this;
    }

    public ShipBuilder<S> isVisible(boolean isVisible) {
      this.isVisible = isVisible;
      return this;
    }

    public ShipBuilder<S> weapon(Weapon<?> weapon) {
      this.weapon = weapon;
      return this;
    }

    public ShipBuilder<S> status(Status<?> status) {
      this.status = (Status<S>) status;
      return this;
    }

    public ShipBuilder<S> direction(int direction) {
      this.direction = direction;
      return this;
    }

    public ShipBuilder<S> isBoosting(int isBoosting) {
      this.isBoosting = isBoosting;
      return this;
    }

    public Ship<S> build() {
      return new Ship<>(world, position, velocity, markAsRemoved, isCollidable, isVisible, weapon, status, direction,
          isBoosting);
    }
  }
}
