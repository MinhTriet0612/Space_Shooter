package org.example.scene;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.example.Renderable;
import org.example.constant.ScreenAttributeConstant;
import org.example.entity.Ship;
import org.example.graphic.FPSCounter;
import org.example.graphic.Healthbar;
import org.example.input.ControllerInput;
import org.example.item.Shotgun;
import org.example.item.Lazergun;
import org.example.system.controller.ShipController;
import org.example.util.AssetManager;
import org.example.util.Vector2D;
import org.example.world.CasualWorld;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class CasualPlayScene extends Scene {
  private CasualWorld world = new CasualWorld();
  private final Ship<?> ship = new Ship<>();
  private final Healthbar healthbar = new Healthbar();
  private final Background bg = new Background();
  private final ControllerInput controllerInput = new ControllerInput();

  static public class Background implements Renderable {
    private final BufferedImage backgroundImage1;
    private final BufferedImage backgroundImage2;
    private int backgroundY1 = 0;
    private int backgroundY2 = -ScreenAttributeConstant.APPSCENE_HEIGHT;


    public Background() {
      this.backgroundImage1 = AssetManager.getBackground();
      this.backgroundImage2 = AssetManager.getBackground();
    }
    

    @Override
    public void update(float deltaTime) {
      backgroundY1 += 1;
      backgroundY2 += 1;
      if (backgroundY1 >= ScreenAttributeConstant.APPSCENE_HEIGHT) {
        backgroundY1 = -ScreenAttributeConstant.APPSCENE_HEIGHT;
      }
      if (backgroundY2 >= ScreenAttributeConstant.APPSCENE_HEIGHT) {
        backgroundY2 = -ScreenAttributeConstant.APPSCENE_HEIGHT;
      }
    }

    @Override
    public void render(Graphics g) {
      g.drawImage(this.backgroundImage1, 0, backgroundY1,
        ScreenAttributeConstant.APPSCENE_WIDTH, ScreenAttributeConstant.APPSCENE_HEIGHT, null);
      g.drawImage(this.backgroundImage2, 0, backgroundY2, 
        ScreenAttributeConstant.APPSCENE_WIDTH, ScreenAttributeConstant.APPSCENE_HEIGHT, null);
    }
  }

  public CasualPlayScene() {
    super();
    // Lazergun lazergun = this.world.addItem(new Lazergun());
    Shotgun shotgun = this.world.addItem(new Shotgun());
    this.ship.setPosition(new Vector2D(ScreenAttributeConstant.APPSCENE_WIDTH / 2,
      ScreenAttributeConstant.APPSCENE_HEIGHT / 2));
    this.ship.setIsBoosting(0);
    this.ship.setWeapon(shotgun);
    this.world.addEntity(ship);
    ShipController shipController = new ShipController(ship, controllerInput);
    this.world.addSystem(shipController);
    this.healthbar.setMortalEntity(ship);

    this.world.setScene(this);
    this.addGraphic(this.bg);
    this.addGraphic(this.healthbar);
  }

  @Override
  public void onShow() {
    this.setSize(this.getApp().getSize());
    this.addGraphic(new FPSCounter(this.getApp()));
    this.getApp().addKeyListener(controllerInput);
  }

  @Override
  public void onDispose() {
    this.getApp().removeKeyListener(controllerInput);
  }

  @Override
  public void update(float deltaTime) {
    super.update(deltaTime);
    this.world.update(deltaTime);
  }

  @Override
  public void render(Graphics g) {
    super.render(g);
    this.world.render(g);
  }
}
