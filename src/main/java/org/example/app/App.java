package org.example.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.example.common.ScreenAttributeConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class App extends JFrame {
    private List<Scene> screens = new ArrayList<>();
    private Scene currentScene;

    public App(Scene scene) {
        this.pack();
        this.add(scene);
        this.screens.add(scene);
        this.setVisible(true);
        this.currentScene = scene;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(
            ScreenAttributeConstant.APPSCENE_WIDTH, 
            ScreenAttributeConstant.APPSCENE_HEIGHT
        );
    }

    public void start() {
        this.currentScene.run();
        this.currentScene.onShow();
    }

    public void switchTo(Scene scene) {
        this.currentScene = scene;
    }

    public void addScene(Scene scene) {
        this.screens.add(scene);
    }
}