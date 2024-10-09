package org.example.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.ShipStats;
import org.example.system.status.Status;
import org.example.util.AssetManager;
import org.example.util.DeepCopyUtils;
import org.example.util.GraphicsUtil;
import org.example.util.Response;
import org.example.util.GraphicsUtil.DrawMode;

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

  public Ship() {
    super();
  }

  public Rigid getRigid() {
    return new Circle(getPosition(), this.getStatus().getInitStats().getSize());
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    GraphicsUtil.drawImage(g, this.sprites[this.isBoosting][this.direction].getScaledInstance(
        40, 70, Image.SCALE_DEFAULT), (int) this.getPosition().getX(), (int) this.getPosition().getY(), 40, 70,
        DrawMode.CENTER);
  }

  @Override
  public void useWeapon() {
    this.getWeapon().fire(this.getPosition());
  }

  @Override
  public void update(float deltaTime) {
  }

  @Override
  public void onAdd() {
    this.setDebugRigid(true);
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
    if (other instanceof Bullet) {
      other.setMarkAsRemoved(true);
      this.getStatus().getCurrentStats().setHealth(this.getStatus().getCurrentStats().getHealth() - 5);
    }
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
}
