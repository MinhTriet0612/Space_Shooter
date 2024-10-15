package org.example.scene;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import org.example.App;
import org.example.Renderable;
import org.example.rigid.Rectangle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Scene extends JPanel implements Renderable {
  private App app;
  private List<Renderable> sGraphics = new LinkedList<>();

  public Scene() {
  }

  public abstract void onShow();

  public abstract void onDispose();

  public void update(float deltaTime) {
    Iterator<Renderable> it = this.sGraphics.iterator();
    
    while (it.hasNext()) {
      Renderable graphic = it.next();
      graphic.update(deltaTime);
    }
    
  }

  public void render(Graphics g) {
    Iterator<Renderable> it = this.sGraphics.iterator();
    
    while (it.hasNext()) {
      Renderable graphic = it.next();
      graphic.render(g);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.render(g);
  }

  public Rectangle getViewportRigid() {
    return new Rectangle(0, 0, this.getWidth(), this.getHeight());
    // return new Rectangle(300, 300, this.getScene().getWidth() - 600,
    // this.getScene().getHeight() - 600);
  }

  public void addGraphic(Renderable graphic) {
    this.sGraphics.add(graphic);
  }

  public void removeGraphic(Renderable graphic) {
    this.sGraphics.remove(graphic);
  }
}
