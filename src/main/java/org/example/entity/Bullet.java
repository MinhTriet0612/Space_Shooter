package org.example.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

import org.example.rigid.Rigid;
import org.example.stats.BulletStats;
import org.example.util.AssetManager;
import org.example.util.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class Bullet extends Entity<BulletStats> {
  private int direct;
  private final BufferedImage[][] bullets = AssetManager.getLazerBoltAssets();
  private final Timer bulletAnimationTimer = new Timer(150, e -> this.direct = this.direct != 0 ? 0 : 1);

  public Rigid getRigid() {
    return null;
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(
        this.bullets[0][this.direct].getScaledInstance(50, 50, Image.SCALE_DEFAULT),
        this.getPosition().getX(),
        this.getPosition().getY(),
        null);
  }

  @Override
  public void update(float deltaTime) {
    this.position.setY(this.position.getY() - 5);
    // this.position.setX(this.position.getX() + this.direct == 1 ? -5 : 5);
  }

  @Override
  public void onCollisionStay(Entity other, Response response) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onCollisionStay'");
  }

  @Override
  public void onCollisionExit(Entity other, Response response) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onCollisionExit'");
  }

  @Override
  public void onCollisionEnter(Entity other, Response response) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onCollisionEnter'");
  }
}
