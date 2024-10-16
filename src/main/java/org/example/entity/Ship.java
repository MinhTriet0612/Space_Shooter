package org.example.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import org.example.item.Weapon;
import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.ShipStats;
import org.example.system.status.Status;
import org.example.util.AssetManager;
import org.example.util.Response;
import org.example.util.TimerManager;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship<S extends ShipStats> extends MortalEntity<S> {
  private float rotation = (float) (-Math.PI / 2);
  private Status<S> status = new Status<S>((S) new ShipStats());
  private Weapon<?> weapon;
  private final BufferedImage[][] sprites = AssetManager.getShipAssets();
  protected int isBoosting;
  private final Timer boostTimer = TimerManager.createTimer(100, e -> {
    this.updateShipBoost();
  });
  private MovingState movingState = MovingState.NEUTRAL;

  static enum MovingState {
    NEUTRAL(2), LEFT(0), RIGHT(4);

    private final int value;

    private MovingState(int value) {
      this.value = value;
    }

    public int getValue() {
      return this.value;
    }
  }

  public Ship() {
    super();
  }

  public void updateShipBoost() {
    this.isBoosting = (this.isBoosting == 0) ? 1 : 0;
  }

  public Rigid getRigid() {
    return new Circle(getPosition(), this.getStatus().getInitStats().getSize());
  }

  @Override
  public void update(float deltaTime) {
    if (this.getVelocity().getX() > 0) {
      this.setMovingState(MovingState.RIGHT);
    } else if (this.getVelocity().getX() < 0) {
      this.setMovingState(MovingState.LEFT);
    } else {
      this.setMovingState(MovingState.NEUTRAL);
    }

    super.update(deltaTime);
    this.weapon.update(deltaTime);
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    Graphics2D g2d = (Graphics2D) g;
    Image image = this.sprites[this.isBoosting][this.getMovingState().getValue()].getScaledInstance(40, 70,
        Image.SCALE_DEFAULT);
    int centerX = image.getWidth(null) / 2;
    int centerY = image.getHeight(null) / 2;
    AffineTransform transform = new AffineTransform();
    transform.translate(this.getPosition().getX() - centerX, this.getPosition().getY() - centerY);
    transform.rotate(this.getRotation() + Math.PI / 2, centerX, centerY);
    g2d.drawImage(image, transform, null);
  }

  @Override
  public void primaryAction() {
    this.getWeapon().setFiring(true);
  }

  @Override
  public void onReady() {
    this.boostTimer.start();
  }

  @Override
  public void onRemove() {
    this.boostTimer.stop();
  }

  @Override
  public void onCollisionStay(Entity<?> other, Response<Entity<?>> response) {
  }

  @Override
  public void onCollisionExit(Entity<?> other, Response<Entity<?>> response) {
  }

  @Override
  public void onCollisionEnter(Entity<?> other, Response<Entity<?>> response) {
    if (other instanceof Bullet) {
      this.injure((int) (Math.random() * 10) + 5);
    }
  }

  public void moveUp() {
    this.getVelocity().setY(-this.getCurrentStats().getSpeed());
  }

  public void moveDown() {
    this.getVelocity().setY(this.getCurrentStats().getSpeed());
  }

  public void moveLeft() {
    this.getVelocity().setX(-this.getCurrentStats().getSpeed());
  }

  public void moveRight() {
    this.getVelocity().setX(this.getCurrentStats().getSpeed());
  }

  public void idle() {
  }

  public void setWeapon(Weapon<?> weapon) {
    weapon.setOwner(this);
    this.weapon = weapon;
  }
}
