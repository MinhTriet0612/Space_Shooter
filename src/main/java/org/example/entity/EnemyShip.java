package org.example.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import org.example.item.Weapon;
import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.EnemyShipStats;
import org.example.system.status.Status;
import org.example.util.Response;
import org.example.util.Vector2D;
import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnemyShip extends Ship<EnemyShipStats> {

  public EnemyShip(World world, Vector2D position, Vector2D velocity, boolean markAsRemoved, boolean isCollidable,
      boolean isVisible, Weapon<?> weapon, Status<EnemyShipStats> status, int direction, int isBoosting) {
    super(world, position, velocity, markAsRemoved, isCollidable, isVisible, weapon, status, direction, isBoosting);
  }

  private Status<EnemyShipStats> status = new Status<>(new EnemyShipStats());

  private BufferedImage[][] sprites;
  private int isBoosting = 0;
  private int direction = 2;
  private int speed = 6;
  private Timer boostTimer;
  private Timer limitFireRate;
  private int fireRate = 150;
  private boolean canFire = true;

  public Rigid getRigid() {
    return new Circle(this.getPosition(), this.status.getInitStats().getSize() * 50);
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(this.sprites[this.isBoosting][this.direction].getScaledInstance(
        50, 100, Image.SCALE_DEFAULT), (int) this.getPosition().getX(), (int) this.getPosition().getY(), null);
  }

  @Override
  public void update(float deltaTime) {
    this.getPosition().setY(this.getPosition().getY() + 1);
  }

  @Override
  public void onCollisionStay(Entity<?> other, Response response) {
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

  public void moveUp() {
    if (this.getPosition().getY() > 2) {
      this.getPosition().setY(this.getPosition().getY() - this.speed);
    }
  }

  public void moveDown() {
    if (this.getPosition().getY() < 600 - 50) {
      this.getPosition().setY(this.getPosition().getY() + this.speed);
    }
  }

  public void moveLeft() {
    if (this.getPosition().getX() > 0) {
      this.getPosition().setX(this.getPosition().getX() - this.speed);
    }
    if (this.direction > 0) {
      this.direction -= 1;
    }
  }

  public void moveRight() {
    if (this.getPosition().getX() < 800 - 126) {
      this.getPosition().setX(this.getPosition().getX() + this.speed);
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

  @Override
  public void useWeapon() {
    // throw new UnsupportedOperationException("Not supported yet.");
  }

  public static EnemyShip.EnemyShipBuilder builder() {
    return new EnemyShip.EnemyShipBuilder();
  }

  public static class EnemyShipBuilder extends Ship.ShipBuilder<EnemyShipStats> {
    private World world;
    private Vector2D position;
    private Vector2D velocity;
    private boolean markAsRemoved;
    private boolean isCollidable;
    private boolean isVisible;
    private Weapon<?> weapon;
    private int direction;
    private int isBoosting;
    private Status<EnemyShipStats> status;

    public EnemyShipBuilder world(World world) {
      this.world = world;
      return this;
    }

    public EnemyShipBuilder position(Vector2D position) {
      this.position = position;
      return this;
    }

    public EnemyShipBuilder velocity(Vector2D velocity) {
      this.velocity = velocity;
      return this;
    }

    public EnemyShipBuilder markAsRemoved(boolean markAsRemoved) {
      this.markAsRemoved = markAsRemoved;
      return this;
    }

    public EnemyShipBuilder isCollidable(boolean isCollidable) {
      this.isCollidable = isCollidable;
      return this;
    }

    public EnemyShipBuilder isVisible(boolean isVisible) {
      this.isVisible = isVisible;
      return this;
    }

    public EnemyShipBuilder weapon(Weapon<?> weapon) {
      this.weapon = weapon;
      return this;
    }

    public EnemyShipBuilder status(Status<?> status) {
      this.status = (Status<EnemyShipStats>) status;
      return this;
    }

    public EnemyShipBuilder direction(int direction) {
      this.direction = direction;
      return this;
    }

    public EnemyShipBuilder isBoosting(int isBoosting) {
      this.isBoosting = isBoosting;
      return this;
    }

    public EnemyShip build() {
      return new EnemyShip(world, position, velocity, markAsRemoved, isCollidable, isVisible, weapon, status, direction,
          isBoosting);
    }
  }

}
