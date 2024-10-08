package org.example.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.Timer;

import org.example.item.Weapon;
import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.MonsterStats;
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
public class Monster extends MortalEntity<MonsterStats> {

  protected Monster(World world, Vector2D position, Vector2D velocity, boolean markAsRemoved, boolean isCollidable,
      boolean isVisible, Weapon<?> weapon) {
    super(world, position, velocity, markAsRemoved, isCollidable, isVisible, weapon);
  }

  private Status<MonsterStats> status = new Status<>(new MonsterStats());

  private int speed;
  private int direction;
  private int isBoosting;
  private int movePoint;
  private final BufferedImage[][] sprites = AssetManager.getEnemyAssets();
  private final Timer boostTimer = new Timer(130, e -> this.updateShipBoost());
  private final Timer movePointTimer = new Timer(1000, e -> this.updatedMovePoint());
  private final List<Integer> 
      verRange = List.of(1, 2, 3, 4, 0, 1, 2, 3, 4, 0), // doc
      horRange = List.of(1, 2, 3, 4, 0, -1, -2, -3, -4, -0); // ngang

  public Monster(World world, Vector2D position, Vector2D velocity, boolean markAsRemoved, boolean isCollidable,
      boolean isVisible, Weapon<?> weapon, Status<MonsterStats> status, int speed, int direction, int isBoosting,
      int movePoint) {
    super(world, position, velocity, markAsRemoved, isCollidable, isVisible, weapon);
    this.status = status;
    this.speed = speed;
    this.direction = direction;
    this.isBoosting = isBoosting;
    this.movePoint = movePoint;
  }

  public Rigid getRigid() {
    return new Circle(this.getPosition(), this.status.getInitStats().getSize());
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(this.sprites[isBoosting][direction].getScaledInstance(
        30, 55, Image.SCALE_AREA_AVERAGING), (int) this.getPosition().getX(), (int) this.getPosition().getY(), null);
  }

  @Override
  public void useWeapon() {
    Bullet bullet = this.getWeapon().fire(DeepCopyUtils.copy(this.getPosition()));
    if (bullet != null)
      this.getWorld().addEntity(bullet);
  }

  @Override
  public void onAdd() {
    this.startTimer();
  }

  @Override
  public void update(float deltaTime) {
    this.getPosition().setY(this.getPosition().getY() + this.verRange.get(this.movePoint));
    this.getPosition().setX(this.getPosition().getX() + this.horRange.get(this.movePoint));
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
    this.movePointTimer.start();
  }

  public void updatedMovePoint() {
    this.movePoint = (int) Math.round(Math.random() * (this.verRange.size() - 1));
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
    if (this.getPosition().getX() < 600 - 126) {
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

  public static Monster.MonsterBuilder builder() {
    return new Monster.MonsterBuilder();
  }

  public static class MonsterBuilder {
    private World world;
    private Vector2D position;
    private Vector2D velocity;
    private boolean markAsRemoved;
    private boolean isCollidable;
    private boolean isVisible;
    private Weapon<?> weapon;
    private Status<MonsterStats> status;
    private int speed;
    private int direction;
    private int isBoosting;
    private int movePoint;

    public MonsterBuilder world(World world) {
      this.world = world;
      return this;
    }

    public MonsterBuilder position(Vector2D position) {
      this.position = position;
      return this;
    }

    public MonsterBuilder velocity(Vector2D velocity) {
      this.velocity = velocity;
      return this;
    }

    public MonsterBuilder markAsRemoved(boolean markAsRemoved) {
      this.markAsRemoved = markAsRemoved;
      return this;
    }

    public MonsterBuilder isCollidable(boolean isCollidable) {
      this.isCollidable = isCollidable;
      return this;
    }

    public MonsterBuilder isVisible(boolean isVisible) {
      this.isVisible = isVisible;
      return this;
    }

    public MonsterBuilder weapon(Weapon<?> weapon) {
      this.weapon = weapon;
      return this;
    }

    public MonsterBuilder status(Status<MonsterStats> status) {
      this.status = status;
      return this;
    }

    public MonsterBuilder speed(int speed) {
      this.speed = speed;
      return this;
    }

    public MonsterBuilder direction(int direction) {
      this.direction = direction;
      return this;
    }

    public MonsterBuilder isBoosting(int isBoosting) {
      this.isBoosting = isBoosting;
      return this;
    }

    public MonsterBuilder movePoint(int movePoint) {
      this.movePoint = movePoint;
      return this;
    }

    public Monster build() {
      return new Monster(world, position, velocity, markAsRemoved, isCollidable, isVisible, weapon);
    }
  }

}
