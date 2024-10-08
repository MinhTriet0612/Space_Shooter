package org.example.system.controller;

import org.example.entity.Ship;
import org.example.input.ControllerInput;
import java.awt.event.KeyEvent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipController extends ControllerSystem {
  private Ship<?> ship;
  private ControllerInput controllerInput;

  public ShipController(Ship<?> ship, ControllerInput controllerInput) {
    this.ship = ship;
    this.controllerInput = controllerInput;
  }

  @Override
  public void update(float d) {
    boolean up = controllerInput.isKeyDown(KeyEvent.VK_W) || controllerInput.isKeyDown(KeyEvent.VK_UP);
    boolean down = controllerInput.isKeyDown(KeyEvent.VK_S) || controllerInput.isKeyDown(KeyEvent.VK_DOWN);
    boolean left = controllerInput.isKeyDown(KeyEvent.VK_A) || controllerInput.isKeyDown(KeyEvent.VK_LEFT);
    boolean right = controllerInput.isKeyDown(KeyEvent.VK_D) || controllerInput.isKeyDown(KeyEvent.VK_RIGHT);
    boolean space = controllerInput.isKeyDown(KeyEvent.VK_SPACE);

    if (up) {
      this.ship.moveUp();
    } else if (down) {
      this.ship.moveDown();
    } else if (left) {
      this.ship.moveLeft();
    } else if (right) {
      this.ship.moveRight();
    } else {
      this.ship.reRenderDirection();
    }

    if (space)
      this.ship.useWeapon();
  }
}
