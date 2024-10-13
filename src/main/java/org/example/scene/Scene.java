package org.example.scene;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

import org.example.App;
import org.example.Renderable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Scene extends JPanel implements Renderable {
  private App app;
  private LinkedList<Renderable> sGraphics = new LinkedList<>();

  public Scene() {
  }

  public abstract void onShow();

  public abstract void onDispose();

  public void update(float deltaTime) {
    for (int i = 0; i < this.sGraphics.size(); i++) {
      this.sGraphics.get(i).update(deltaTime);
    }
  }

  public void render(Graphics g) {
    for (int i = 0; i < this.sGraphics.size(); i++) {
      this.sGraphics.get(i).render(g);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.render(g);
  }
}
