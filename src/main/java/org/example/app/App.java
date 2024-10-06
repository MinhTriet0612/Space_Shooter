package org.example.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.example.common.ScreenAttributeConstant;
import org.example.exception.InvalidDataException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class App extends JFrame {
    private Scene currentScene;
    private List<Scene> screens = new ArrayList<Scene>();

    public App(Scene scene) {
        this.pack();
        this.add(scene);
        this.screens.add(scene);
        this.setVisible(true);
        this.currentScene = scene;
        this.setSize(
            ScreenAttributeConstant.APPSCENE_WIDTH, 
            ScreenAttributeConstant.APPSCENE_HEIGHT
        );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start() {
        this.currentScene.run();
        this.currentScene.onShow();
    }

    public void switchTo(Scene scene) {
        if (this.currentScene != null) {
            this.currentScene.inDispose();
            this.currentScene = scene;
            start();
        } throw new InvalidDataException("Current scene in app invalid value");
    }

    public void addScene(Scene scene) {
        this.screens.add(scene);
    }
}