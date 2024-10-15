package org.example.graphic;

import org.example.App;
import org.example.Renderable;

import java.awt.Font;
import java.awt.Graphics;

public class FPSCounter implements Renderable {
  private App app;

  public FPSCounter(App app) {
    this.app = app;
  }

  @Override
  public void update(float deltaTime) {
  }

  @Override
  public void render(Graphics g) {
    g.setFont(new Font("Arial", Font.BOLD, 24));
    g.drawString("FPS: " + this.app.getCurrentFPS(), 20, 40);
  }
}
