package org.example.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import org.example.rigid.Circle;
import org.example.rigid.Rectangle;
import org.example.rigid.Rigid;
import org.example.stats.ShipStats;
import org.example.system.status.Status;
import org.example.util.AssetManager;
import org.example.util.DeepCopyUtils;
import org.example.util.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship<S extends ShipStats> extends MortalEntity<S> {
  @SuppressWarnings("unchecked")
  private Status<S> status = new Status<S>((S) new ShipStats());

  private int direction;
  private int isBoosting;
  private final BufferedImage[][] sprites = AssetManager.getShipAssets();
  private final Timer boostTimer = new Timer(130, e -> {
    this.updateShipBoost();
  });

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
    // this.reloadTimer.start();
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

  // public Integer getIsBoosting() {
  // return isBoosting;
  // }
  // public Integer getDirection() {
  // return direction;
  // }
  // public void setIsBoosting(Integer isBoosting) {
  // this.isBoosting = isBoosting;
  // }
  // public void setDirection(Integer direction) {
  // this.direction = direction;
  // }
}
