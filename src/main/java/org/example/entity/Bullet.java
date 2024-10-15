package org.example.entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.Timer;
import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.BulletStats;
import org.example.system.status.Status;
import org.example.util.AssetManager;
import org.example.util.Response;
import org.example.util.SAT;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bullet extends Entity<BulletStats> {
  static final int SIZE = 50;
  private int spriteFrame;
  private Status<BulletStats> status = new Status<>(new BulletStats());
  private int type = 0;
  private final BufferedImage[][] sprites = AssetManager.getLazerBoltAssets();
  private final Timer bulletAnimationTimer = new Timer(150, e -> this.spriteFrame = this.spriteFrame != 0 ? 0 : 1);

  public enum BulletType {
    NORMAL, LASER
  }

  public Bullet() {

  }

  public Rigid getRigid() {
    return new Circle(this.getPosition(), this.getStatus().getInitStats().getRadius());
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    Graphics2D g2d = (Graphics2D) g;
    Image image = this.sprites[type][this.spriteFrame].getScaledInstance(SIZE, SIZE, Image.SCALE_DEFAULT);
    int centerX = image.getWidth(null) / 2;
    int centerY = image.getHeight(null) / 2;
    AffineTransform transform = new AffineTransform();
    transform.translate(this.getPosition().getX() - centerX, this.getPosition().getY() - centerY);
    transform.rotate(this.getRotation(), centerX, centerY);
    g2d.drawImage(image, transform, null);
  }

  @Override
  public void update(float deltaTime) {
    super.update(1f);
    Response<Rigid> response = SAT.testCollide(this.getRigid(), this.getWorld().getScene().getViewportRigid());
    if (!response.isColliding()) {
      this.setMarkAsRemoved(true);
    }
  }

  @Override
  public void onReady() {
    this.bulletAnimationTimer.start();
  }

  @Override
  public void onCollisionStay(Entity<?> other, Response<Entity<?>> response) {
  }

  @Override
  public void onCollisionExit(Entity<?> other, Response<Entity<?>> response) {
  }

  @Override
  public void onCollisionEnter(Entity<?> other, Response<Entity<?>> response) {
    if (other instanceof Ship) {
      this.setMarkAsRemoved(true);
    }
  }
}
