package org.example.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import org.example.constant.ScreenAttributeConstant;
import org.example.rigid.Circle;
import org.example.rigid.Rigid;
import org.example.stats.BulletStats;
import org.example.system.status.Status;
import org.example.util.AssetManager;
import org.example.util.GraphicsUtils;
import org.example.util.Response;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bullet extends Entity<BulletStats> {
  private int direct;
  private Status<BulletStats> status = new Status<>(new BulletStats());
  private int type = 0;
  private final BufferedImage[][] bullets = AssetManager.getLazerBoltAssets();
  private final Timer bulletAnimationTimer = new Timer(150, e -> this.direct = this.direct != 0 ? 0 : 1);

  public Bullet() {
  }

  public Rigid getRigid() {
    return new Circle(this.getPosition(), this.getStatus().getInitStats().getRadius());
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    GraphicsUtils.drawImage(g, this.bullets[type][this.direct].getScaledInstance(50, 50, Image.SCALE_DEFAULT),
        (int) this.getPosition().getX(), (int) this.getPosition().getY(), 50, 50, GraphicsUtils.DrawMode.CENTER);
  }

  @Override
  public void update(float deltaTime) {
    super.update(1f);
    // if (this.getPosition().distance(new
    // Vector2D(ScreenAttributeConstant.APPSCENE_WIDTH / 2,
    // ScreenAttributeConstant.APPSCENE_HEIGHT / 2)) >
    // ScreenAttributeConstant.APPSCENE_HEIGHT) {
    // this.setMarkAsRemoved(true);
    // }
  }

  @Override
  public void onReady() {
    this.bulletAnimationTimer.start();
  }

  @Override
  public void onCollisionStay(Entity<?> other, Response response) {
  }

  @Override
  public void onCollisionExit(Entity<?> other, Response response) {
  }

  @Override
  public void onCollisionEnter(Entity<?> other, Response response) {
    if (other instanceof Ship) {
      this.setMarkAsRemoved(true);
    }
  }
}
