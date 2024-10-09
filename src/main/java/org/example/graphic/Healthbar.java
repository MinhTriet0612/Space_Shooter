package org.example.graphic;

import java.awt.Color;
import java.awt.Graphics;

import org.example.IGraphic;
import org.example.constant.ScreenAttributeConstant;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Healthbar implements IGraphic {
  private Vector2D position = new Vector2D(
      ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH / 2,
      ScreenAttributeConstant.CASUALPLAYSCENE_HEIGHT - 100);
  private int width = 200;
  private int height = 20;
  private int percent = 100;

  public Healthbar() {
  }

  public void render(Graphics g) {
    g.translate((int) this.position.getX() - this.width / 2, (int) this.position.getY());

    g.setColor(Color.BLACK);
    g.drawRect(-2, -2,
        this.width + 2, this.height + 2);

    g.setColor(Color.RED);
    g.fillRect(0, 0,
        this.width, this.height);

    g.setColor(Color.GREEN);
    g.fillRect(0, 0,
        this.width * this.percent / 100, this.height);

    // // draw the border
    // g.setColor(Color.BLACK);
    // g.drawRect((int) this.position.getX(), (int) this.position.getY(),
    // this.width, this.height);

    // // draw the blood inside
    // g.setColor(Color.RED);
    // g.fillRect((int) this.position.getX(), (int) this.position.getY(),
    // this.width, this.height);
  }

  public void update(float deltaTime) {
  }

  public void setPercent(int percent) {
    this.percent = percent;
    if (this.percent < 0) {
      this.percent = 0;
    }
  }
}
