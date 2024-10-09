package org.example.scene;

import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

import org.example.App;
import org.example.IGraphic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Scene extends JPanel implements Runnable {
  private boolean isRunning = true;
  private final Thread thread = new Thread(this);
  private LinkedList<IGraphic> sGraphics = new LinkedList<>();
  private App app;

  public abstract void onShow();

  public abstract void onDispose();

  @Override
  public void run() {
    while (this.isRunning) {
      this.update(1f);
      this.repaint();
      try {
        Thread.sleep(16);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void update(float deltaTime) {
    for (int i = 0; i < this.sGraphics.size(); i++) {
      this.sGraphics.get(i).update(deltaTime);
    }
  }

  public void render(Graphics g) {

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.render(g);
  }
}
