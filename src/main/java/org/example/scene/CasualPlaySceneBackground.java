package org.example.scene;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.example.constant.ScreenAttributeConstant;
import org.example.util.AssetManager;

public class CasualPlaySceneBackground {
  private final BufferedImage backgroundImage;

  public CasualPlaySceneBackground() {
    this.backgroundImage = AssetManager.getBackground();
  }

  public void draw(Graphics g) {
    g.drawImage(this.backgroundImage, 0, 0,
        ScreenAttributeConstant.APPSCENE_WIDTH, ScreenAttributeConstant.APPSCENE_HEIGHT, null);
  }
}
