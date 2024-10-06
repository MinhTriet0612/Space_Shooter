package org.example.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class AssetManager {
  private static BufferedImage[][] sprites;
  private static BufferedImage[][] lazers;
  private static BufferedImage[][] enemies;

  public static BufferedImage[][] getEnemyAssets() {
    if (AssetManager.enemies != null) {
      return AssetManager.enemies;
    }
    int width = 16, height = 24;

    String filePath1 = "/assets/spritesheets/enemy.png";
    String filePath2 = "/assets/spritesheets/enemy.png";

    List<String> paths = List.of(filePath1, filePath2);

    AssetManager.enemies = new BufferedImage[2][5];

    try {
      BufferedImage image = ImageIO
          .read(AssetManager.class.getResource(paths.get((int) Math.round(Math.random() * 1))));
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 5; j++) {
          enemies[i][j] = image.getSubimage(j * width, i * height, width, height);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return enemies;
  }

  public static BufferedImage[][] getShipAssets() {
    if (AssetManager.sprites != null) {
      return AssetManager.sprites;
    }

    int width = 16, height = 24;

    AssetManager.sprites = new BufferedImage[2][5];
    String filePath = "/assets/spritesheets/ship.png";

    try {
      BufferedImage image = ImageIO.read(AssetManager.class.getResource(filePath));
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 5; j++) {
          sprites[i][j] = image.getSubimage(j * width, i * height, width, height);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return sprites;
  }

  public static BufferedImage[][] getLazerBoltAssets() {
    // if (AssetManager.lazers != null) {
    // return AssetManager.lazers;
    // }

    int width = 16, height = 16;

    List<String> paths = List.of("/assets/spritesheets/power-up.png", "/assets/spritesheets/laser-bolts.png");
    AssetManager.lazers = new BufferedImage[2][2];

    try {
      BufferedImage image = ImageIO
          .read(AssetManager.class.getResource(paths.get((int) Math.round(Math.random() * 1))));
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
          lazers[i][j] = image.getSubimage(j * width, i * height, width, height);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
    return lazers;
  }

  public static BufferedImage getBackground() {
    String filePath = "/assets/River/PNG/background.png";
    BufferedImage background = null;

    try {
      background = ImageIO.read(AssetManager.class.getResource(filePath));
    } catch (IOException e) {
      e.printStackTrace();

    }
    return background;
  }
}
