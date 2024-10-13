package org.example.entity;

import java.awt.image.BufferedImage;
import javax.swing.Timer;
import org.example.stats.EnemyShipStats;
import org.example.system.status.Status;
import org.example.util.AssetManager;
import org.example.util.RandomSelector;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnemyShip extends Ship<EnemyShipStats> {
  private float rotation = (float) (Math.PI / 2);
  private Status<EnemyShipStats> status = new Status<>(new EnemyShipStats());
  private final BufferedImage[][] sprites = AssetManager.getEnemyAssets();
  private final Timer moveTimer = new Timer(1000, e -> this.move());

  private float moveAngle = 0f;
  private final String[] actions = { "move", "stay", "shoot", "move:shoot" };
  private final double[] probabilities = { 0.30, 0.10, 0.1 };

  public EnemyShip() {
    super();
  }

  @Override
  public void onReady() {
    super.onReady();
    this.moveTimer.start();
  }

  @Override
  public void update(float deltaTime) {
    super.update(1f);
  }

  public void move() {
    String action = RandomSelector.random(actions, probabilities);
    if (action.equals("move")) {
      this.moveAngle = (float) (Math.random() * 2 * Math.PI);
      this.setVelocity(Vector2D.fromAngle(this.moveAngle).scale(3f));
    } else if (action.equals("stay")) {
      this.getVelocity().setX(0);
      this.getVelocity().setY(0);
    } else if (action.equals("shoot")) {
      this.primaryAction();
    } else if (action.equals("move:shoot")) {
      this.moveAngle = (float) (Math.random() * 2 * Math.PI);
      this.setVelocity(Vector2D.fromAngle(this.moveAngle).scale(3f));
      this.primaryAction();
    }
  }

  @Override
  public void primaryAction() {
    this.getWeapon().setFiring(true);
  }
}
