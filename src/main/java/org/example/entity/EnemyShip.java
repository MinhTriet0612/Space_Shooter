package org.example.entity;

import java.awt.image.BufferedImage;
import javax.swing.Timer;

import org.example.graphic.Healthbar;
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
  private final Healthbar healthbar = new Healthbar();

  private enum Movement {
    Move, Shoot, Stay, MoveShoot, StayShoot
  }

  private float moveAngle = 0f;
  private final double[] probabilities = { 0.2, 0.2, 0.2, 0.2 };

  public EnemyShip() {
    super();
  }

  @Override
  public void onReady() {
    super.onReady();
    this.moveTimer.start();

    // setup healthbar (just testing)
    healthbar.setWidth(100);
    healthbar.setHeight(10);
    healthbar.setMortalEntity(this);
    healthbar.followPos(this.getPosition(), 0, -20);
    this.getWorld().getScene().addGraphic(healthbar);
  }

  @Override
  public void onDeath() {
    this.setMarkAsRemoved(true);
    this.getWorld().getScene().removeGraphic(healthbar);
  }

  @Override
  public void update(float deltaTime) {
    super.update(1f);
  }

  public void move() {
    Movement[] actions = { Movement.Move, Movement.Stay, Movement.Shoot, Movement.MoveShoot, Movement.StayShoot };
    Movement action = RandomSelector.random(actions, probabilities);

    switch (action) {
      case Move:
        this.moveAngle = (float) (Math.random() * Math.PI * 2);
        this.setVelocity(new Vector2D((float) Math.cos(this.moveAngle), (float) Math.sin(this.moveAngle)).scale(2));
        break;
      case Stay:
        this.setVelocity(new Vector2D(0, 0));
        break;
      case Shoot:
        this.primaryAction();
        break;
      case MoveShoot:
        this.moveAngle = (float) (Math.random() * Math.PI * 2);
        this.setVelocity(new Vector2D((float) Math.cos(this.moveAngle), (float) Math.sin(this.moveAngle)).scale(2));
        this.primaryAction();
        break;
      case StayShoot:
        this.setVelocity(new Vector2D(0, 0));
        this.primaryAction();
        break;
    }
  }

  @Override
  public void primaryAction() {
    this.getWeapon().setFiring(true);
  }
}
