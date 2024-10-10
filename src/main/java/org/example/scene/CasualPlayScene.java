package org.example.scene;

import java.awt.Dimension;
import java.awt.Graphics;
import org.example.constant.ScreenAttributeConstant;
import org.example.entity.Ship;
import org.example.graphic.Healthbar;
import org.example.input.ControllerInput;
import org.example.item.GatlinGun;
import org.example.item.LazerGun;
import org.example.system.controller.ShipController;
import org.example.util.Vector2D;
import org.example.world.CasualWorld;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CasualPlayScene extends Scene {
  private final Ship<?> ship;
  private CasualWorld world = new CasualWorld();
  private final Healthbar healthbar = new Healthbar();
  private final CasualPlaySceneBackground casualPlaySceneBackground;
  private final ControllerInput controllerInput = new ControllerInput();

  public CasualPlayScene() {
    this.setPreferredSize(
        new Dimension(
            ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH,
            ScreenAttributeConstant.CASUALPLAYSCENE_HEIGHT));
    this.casualPlaySceneBackground = new CasualPlaySceneBackground();
    this.setFocusable(true);

    // LazerGun lazerGun = this.world.addItem(new LazerGun());
    this.ship = new Ship<>();
    // ship.startTimer();
    this.ship.setPosition(new Vector2D(ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH / 2,
        ScreenAttributeConstant.CASUALPLAYSCENE_HEIGHT / 2));
    this.ship.setIsBoosting(0);
    this.ship.setWeapon(new GatlinGun());

    ShipController shipController = new ShipController(ship, controllerInput);
    this.world.setScene(this);
    this.world.addEntity(ship);
    this.world.addSystem(shipController);

    this.getSGraphics().add(this.healthbar);

    this.addKeyListener(controllerInput);
  }

  @Override
  public void onShow() {
    this.getThread().start();
  }

  @Override
  public void onDispose() {
    this.setRunning(false);
    this.getThread().interrupt();
    this.setFocusable(true);
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);
    this.world.update(deltaTime);
    this.healthbar.setPercent(this.ship.getStatus().getCurrentStats().getHealth());
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    this.casualPlaySceneBackground.draw(g);
    this.world.render(g);
    for (int i = 0; i < this.getSGraphics().size(); i++) {
      this.getSGraphics().get(i).render(g);
    }
  }
}
