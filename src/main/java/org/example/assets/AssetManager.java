package org.example.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class AssetManager {
    private static BufferedImage[][] sprites;
    private static BufferedImage[][] lazers;

    public static BufferedImage[][] getEnemyAssets() {

        if (AssetManager.sprites != null) {
            return AssetManager.sprites;
        }

        int width = 16, height = 24;

        AssetManager.sprites = new BufferedImage[1][2];
        String filePath1 = "/assets/spritesheets/enemy-medium.png";
        String filePath2 = "/assets/spritesheets/enemy-big.png";

        List<String> paths = List.of(filePath1, filePath2);

        try {
            BufferedImage image = ImageIO.read(AssetManager.class.getResource(paths.get((int) Math.round(Math.random()*2))));
            for (int i = 0; i < 1; i++) {
                for (int j = 0; j < 2; j++) {
                    sprites[i][j] = image.getSubimage(j * width, i * height, width, height);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprites;
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
        if (AssetManager.lazers != null) {
            return AssetManager.lazers;
        }

        int width = 16, height = 16;
        String filePath = "/assets/spritesheets/laser-bolts.png";
         AssetManager.lazers = new BufferedImage[2][2];
        
        try {
            BufferedImage image = ImageIO.read(AssetManager.class.getResource(filePath));
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