package org.example.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControllerInput implements KeyListener {
  private boolean[] keys;
  private List<Consumer<KeyEvent>> keyTypedListeners = new ArrayList<>();
  private List<Consumer<KeyEvent>> keyPressedListeners = new ArrayList<>();
  private List<Consumer<KeyEvent>> keyReleasedListeners = new ArrayList<>();

  public ControllerInput() {
    this.keys = new boolean[256];
  }

  @Override
  public void keyTyped(KeyEvent e) {
    for (Consumer<KeyEvent> listener : keyTypedListeners) {
      listener.accept(e);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    this.keys[e.getKeyCode()] = true;
    for (Consumer<KeyEvent> listener : keyPressedListeners) {
      listener.accept(e);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    this.keys[e.getKeyCode()] = false;
    for (Consumer<KeyEvent> listener : keyReleasedListeners) {
      listener.accept(e);
    }
  }

  public boolean isKeyDown(int keyCode) {
    return this.keys[keyCode];
  }

  public void addListener(int eventType, Consumer<KeyEvent> listener) {
    switch (eventType) {
      case KeyEvent.KEY_TYPED:
        keyTypedListeners.add(listener);
        break;
      case KeyEvent.KEY_PRESSED:
        keyPressedListeners.add(listener);
        break;
      case KeyEvent.KEY_RELEASED:
        keyReleasedListeners.add(listener);
        break;
    }
  }

  public void removeListener(int eventType, Consumer<KeyEvent> listener) {
    switch (eventType) {
      case KeyEvent.KEY_TYPED:
        keyTypedListeners.remove(listener);
        break;
      case KeyEvent.KEY_PRESSED:
        keyPressedListeners.remove(listener);
        break;
      case KeyEvent.KEY_RELEASED:
        keyReleasedListeners.remove(listener);
        break;
    }
  }
}
