package org.example;

import java.awt.Graphics;

public interface Renderable {
  void update(float deltaTime);

  void render(Graphics g);
}
