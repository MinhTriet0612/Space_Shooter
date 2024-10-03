package org.example.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class App extends JFrame {
    private List<Scene> screens = new ArrayList<>();
    private Scene currentScene;

    public App(Scene scene) {
        this.pack();
        this.screens.add(scene);
        this.currentScene = scene;
        this.setVisible(true);
        this.setSize(800, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(scene);
    }

    public void start() {
        this.currentScene.onShow();
    }

    public void switchTo(Scene scene) {
        this.currentScene = scene;
    }

    public void addScene(Scene scene) {
        this.screens.add(scene);
    }
}