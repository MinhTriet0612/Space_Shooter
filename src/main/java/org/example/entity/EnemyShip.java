package org.example.entity;

import java.awt.image.BufferedImage;
import javax.swing.Timer;

import org.example.graphic.Healthbar;
import org.example.stats.EnemyShipStats;
import org.example.system.status.Status;
import org.example.util.AssetManager;
import org.example.util.PossibilityFactor;
import org.example.util.TimerManager;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnemyShip extends Ship<EnemyShipStats> {
  private float rotation = (float) (Math.PI / 2);
  private Status<EnemyShipStats> status = new Status<>(new EnemyShipStats());
  private final BufferedImage[][] sprites = AssetManager.getEnemyAssets();
  private final Timer moveTimer = TimerManager.createTimer(1000, e -> this.move());
  private final Healthbar healthbar = new Healthbar();
  private final PossibilityFactor movementFactor = new PossibilityFactor();

  static public class Movement {
    public static final int MOVE = 1 << 0;
    public static final int STAY = 1 << 1;
    public static final int SHOOT = 1 << 2;
    public static final int MOVESHOOT = 1 << 3;
    public static final int STAYSHOOT = 1 << 4;
  }

  private float moveAngle = 0f;

  public EnemyShip() {
    super();
  }

  @Override
  public void onReady() {
    super.onReady();
    this.moveTimer.start();

    // setup movement factor
    this.movementFactor.setFactor(this.getInitStats().getMovementFactor());

    // setup healthbar (just testing)
    healthbar.setWidth(100);
    healthbar.setHeight(10);
    healthbar.setMortalEntity(this);
    healthbar.followPos(this.getPosition(), 0, -20);
    this.getWorld().getScene().addGraphic(healthbar);
  }

  @Override
  public void onDeath() {
    this.moveTimer.stop();
    this.setMarkAsRemoved(true);
    this.getWorld().getScene().removeGraphic(healthbar);
  }

  @Override
  public void update(float deltaTime) {
    super.update(1f);
  }

  public void move() {
    int movement = movementFactor.random();
    switch (movement) {
      case Movement.MOVE:
        this.moveAngle = (float) (Math.random() * Math.PI * 2);
        this.setVelocity(new Vector2D((float) Math.cos(this.moveAngle), (float) Math.sin(this.moveAngle))
            .scale(this.getCurrentStats().getSpeed()));
        break;
      case Movement.STAY:
        this.setVelocity(new Vector2D(0, 0));
        break;
      case Movement.SHOOT:
        this.primaryAction();
        break;
      case Movement.MOVESHOOT:
        this.moveAngle = (float) (Math.random() * Math.PI * 2);
        this.setVelocity(new Vector2D((float) Math.cos(this.moveAngle), (float) Math.sin(this.moveAngle))
            .scale(this.getCurrentStats().getSpeed()));
        this.primaryAction();
        break;
      case Movement.STAYSHOOT:
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
