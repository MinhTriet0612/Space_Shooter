package org.example.app;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.example.assets.AssetManager;
import org.example.common.ScreenAttributeConstant;

public class CasualPlaySceneBackground {
    private final BufferedImage backgroundImage;

    public CasualPlaySceneBackground() {
        this.backgroundImage = AssetManager.getBackground();
    }

    public void draw(Graphics g) {
        g.drawImage(this.backgroundImage, 0, 0, 
            ScreenAttributeConstant.APPSCENE_WIDTH, ScreenAttributeConstant.APPSCENE_HEIGHT, null
        );
    }
}