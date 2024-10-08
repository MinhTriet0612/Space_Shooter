package org.example.scene;

import java.awt.Dimension;
import java.awt.Graphics;

import org.example.constant.ScreenAttributeConstant;
import org.example.entity.Ship;
import org.example.input.ControllerInput;
import org.example.system.controller.ShipController;
import org.example.world.CasualWorld;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CasualPlayScene extends Scene {
  private Boolean isRunning;
  private CasualWorld world;
  private final Thread gameThread;
  private final CasualPlaySceneBackground casualPlaySceneBackground;
  private final ControllerInput controllerInput = new ControllerInput();

  public CasualPlayScene() {
    this.isRunning = true;
    this.gameThread = new Thread(this);
    this.setPreferredSize(
        new Dimension(
            ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH,
            ScreenAttributeConstant.CASUALPLAYSCENE_HEIGHT));
    this.casualPlaySceneBackground = new CasualPlaySceneBackground();
    this.setFocusable(true);

    this.world = new CasualWorld();

    // Add ship entity and controller system to world
    Ship playerShip = new Ship();
    ShipController shipController = new ShipController(playerShip, controllerInput);
    this.world.addSystem(shipController);
    this.world.addEntity(playerShip);

    // Register controller input to this scene
    this.addKeyListener(controllerInput);
  }

  @Override
  public void onShow() {
    this.gameThread.start();
  }

  @Override
  public void run() {
    while (isRunning) {
      this.update(1f);
      this.repaint();
      try {
        Thread.sleep(16);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.casualPlaySceneBackground.draw(g);
    this.world.render(g);
  }

  @Override
  public void onDispose() {
    this.isRunning = false;
    this.gameThread.interrupt();
    this.setFocusable(true);
  }

  @Override
  public void update(float deltaTime) {
    this.world.update(1f);
  }
}
