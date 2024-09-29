package org.example.assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class AssetManager {

    private static BufferedImage[][] sprites;
    private static BufferedImage[][] lazers;


    public static BufferedImage[][] getShipAssets() {

        if (AssetManager.sprites != null) {
            return AssetManager.sprites;
        }

        int width = 16, height = 24;

        AssetManager.sprites = new BufferedImage[2][5];
        // how to get the path shorter instead of using the absolute path
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
        BufferedImage[][] lazers = new BufferedImage[2][2];

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