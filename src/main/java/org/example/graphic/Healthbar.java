package org.example.graphic;

import java.awt.Color;
import java.awt.Graphics;

import org.example.Renderable;
import org.example.constant.ScreenAttributeConstant;
import org.example.entity.MortalEntity;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Healthbar implements Renderable {
  private MortalEntity<?> mortalEntity;
  private Vector2D position = new Vector2D(
      ScreenAttributeConstant.APPSCENE_WIDTH / 2,
      ScreenAttributeConstant.APPSCENE_HEIGHT - 100);
  private int width = 200;
  private int height = 20;
  private int percent = 100;
  private int offsetX = 0;
  private int offsetY = 0;

  public Healthbar() {
  }

  @Override
  public void update(float deltaTime) {
    if (this.getMortalEntity() != null) {
      this.setPercent(this.getMortalEntity().getStatus().getCurrentStats().getHealth());
    }
  }

  @Override
  public void render(Graphics g) {
    int translateX = (int) this.position.getX() - this.width / 2 + this.offsetX;
    int translateY = (int) this.position.getY() - this.height / 2 + this.offsetY;
    g.translate(translateX, translateY);

    g.setColor(Color.BLACK);
    g.drawRect(-2, -2,
        this.width + 2, this.height + 2);

    g.setColor(Color.RED);
    g.fillRect(0, 0,
        this.width, this.height);

    g.setColor(Color.GREEN);
    g.fillRect(0, 0,
        this.width * this.percent / 100, this.height);

    g.translate(-translateX, -translateY);
  }

  public void setPercent(int percent) {
    this.percent = percent;
    if (this.percent < 0) {
      this.percent = 0;
    }
  }

  public void followPos(Vector2D position) {
    this.setPosition(position);
  }

  public void followPos(Vector2D position, int offsetX, int offsetY) {
    this.setPosition(position);
    this.setOffsetX(offsetX);
    this.setOffsetY(offsetY);
  }
}
