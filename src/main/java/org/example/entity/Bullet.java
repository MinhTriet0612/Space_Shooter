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
import org.example.util.GraphicsUtil;
import org.example.util.Response;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bullet extends Entity<BulletStats> {
  private int direct;
  private Status<BulletStats> status = new Status<>(new BulletStats());
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
    // g.drawImage(
    // this.bullets[0][this.direct].getScaledInstance(50, 50, Image.SCALE_DEFAULT),
    // (int) this.getPosition().getX(),
    // (int) this.getPosition().getY(),
    // null);
    GraphicsUtil.drawImage(g, this.bullets[0][this.direct].getScaledInstance(50, 50, Image.SCALE_DEFAULT),
        (int) this.getPosition().getX(), (int) this.getPosition().getY(), 50, 50, GraphicsUtil.DrawMode.CENTER);
  }

  @Override
  public void update(float deltaTime) {
    this.getPosition().setY(this.getPosition().getY() - 5);
    if (this.getPosition().distance(new Vector2D(ScreenAttributeConstant.APPSCENE_WIDTH / 2,
        ScreenAttributeConstant.APPSCENE_HEIGHT / 2)) > ScreenAttributeConstant.APPSCENE_HEIGHT) {
      this.setMarkAsRemoved(true);
    }
    // this.position.setX(this.position.getX() + this.direct == 1 ? -5 : 5);
  }

  @Override
  public void onAdd() {
    this.bulletAnimationTimer.start();
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
}
