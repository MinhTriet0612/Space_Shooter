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
import org.example.util.GraphicsUtils;
import org.example.util.Response;
import org.example.util.Vector2D;
import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnemyShip extends Ship<EnemyShipStats> {
  private Status<EnemyShipStats> status = new Status<>(new EnemyShipStats());
  private BufferedImage[][] sprites;
  private int isBoosting = 0;
  private int direction = 2;
  private int speed = 6;
  private Timer boostTimer;
  private Timer limitFireRate;
  private int fireRate = 150;
  private boolean canFire = true;

  public EnemyShip() {
    super();
  }

  public Rigid getRigid() {
    return new Circle(this.getPosition(), this.getStatus().getInitStats().getSize());
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    GraphicsUtils.drawImage(g, this.sprites[this.isBoosting][this.direction].getScaledInstance(
        50, 100, Image.SCALE_DEFAULT), (int) this.getPosition().getX(), (int) this.getPosition().getY(), 50, 100,
        GraphicsUtils.DrawMode.CENTER);
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
}
