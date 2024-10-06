package org.example.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControllerInput implements KeyListener {
  private boolean[] keys;

  public ControllerInput() {
    this.keys = new boolean[256];
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
    this.keys[e.getKeyCode()] = true;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    this.keys[e.getKeyCode()] = false;
  }

  public boolean isKeyDown(int keyCode) {
    return this.keys[keyCode];
  }
}
