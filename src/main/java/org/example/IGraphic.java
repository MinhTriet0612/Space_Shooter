package org.example;

import java.awt.Graphics;

public interface IGraphic {
  void render(Graphics g);

  void update(float deltaTime);
}
