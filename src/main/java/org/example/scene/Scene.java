package org.example.scene;

import javax.swing.JPanel;

import org.example.App;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Scene extends JPanel implements Runnable {
  protected App app;

  public abstract void onShow();

  public abstract void onDispose();

  public abstract void update(float deltaTime);
}
