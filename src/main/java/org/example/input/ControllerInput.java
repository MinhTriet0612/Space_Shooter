package org.example.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.function.Consumer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ControllerInput implements KeyListener {
  private HashSet<Integer> keysDown = new HashSet<>();
  private List<Consumer<KeyEvent>> keyTypedListeners = new ArrayList<>();
  private List<Consumer<KeyEvent>> keyPressedListeners = new ArrayList<>();
  private List<Consumer<KeyEvent>> keyReleasedListeners = new ArrayList<>();

  public ControllerInput() {
  }

  @Override
  public void keyTyped(KeyEvent e) {
    for (Consumer<KeyEvent> listener : keyTypedListeners) {
      listener.accept(e);
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    keysDown.add(e.getKeyCode());
    for (Consumer<KeyEvent> listener : keyPressedListeners) {
      listener.accept(e);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keysDown.remove(e.getKeyCode());
    for (Consumer<KeyEvent> listener : keyReleasedListeners) {
      listener.accept(e);
    }
  }

  public boolean isKeyDown(int keyCode) {
    return keysDown.contains(keyCode);
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
