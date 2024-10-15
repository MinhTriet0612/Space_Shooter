package org.example;

import java.util.HashMap;
import javax.swing.JFrame;

import org.example.constant.ScreenAttributeConstant;
import org.example.exception.InvalidDataException;
import org.example.scene.CasualPlayScene;
import org.example.scene.MainMenuScene;
import org.example.scene.Scene;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class App extends JFrame implements Runnable {
  private final Thread thread = new Thread(this);
  private HashMap<Class<? extends Scene>, Scene> scenes = new HashMap<>();
  private Scene currentScene;
  private int currentFPS = 0;

  public App() {
    this.pack();
    this.setVisible(true);
    this.setSize(ScreenAttributeConstant.APPSCENE_WIDTH, ScreenAttributeConstant.APPSCENE_HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // add scenes
    this.addScene(new CasualPlayScene());
    this.addScene(new MainMenuScene());

    this.switchTo(CasualPlayScene.class);
  }

  @Override
  public void run() {
    // TODO: change this to RefreshRateChecker.getCurrent();
    final int targetFPS = 60;
    final long optimalTime = 1_000_000_000 / targetFPS; // 1 second in nanoseconds / target FPS

    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    long currentTime;
    long elapsedTime;
    long sleepTime;

    int frames = 0; // To count the number of frames

    while (true) {
      currentTime = System.nanoTime();
      elapsedTime = currentTime - lastTime;
      lastTime = currentTime;

      float delta = elapsedTime / 1_000_000_000.0f; // convert elapsed time to seconds
      this.currentScene.update(delta);
      this.repaint(); // render the frame

      frames++; // Increment the frame count

      // Calculate how long to sleep to keep a consistent frame rate
      sleepTime = (lastTime - System.nanoTime() + optimalTime) / 1_000_000;
      if (sleepTime > 0) {
        try {
          Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      // Calculate FPS every second
      if (System.currentTimeMillis() - timer >= 1000) {
        timer += 1000; // Reset the timer for the next second
        this.currentFPS = frames; // Update the FPS value
        frames = 0; // Reset the frame count for the next second
      }
    }
  }

  public void switchTo(Class<? extends Scene> sceneClass) {
    if (this.currentScene != null) {
      this.currentScene.onDispose();
      this.remove(this.currentScene);
    }

    Scene scene = this.scenes.get(sceneClass);
    if (scene != null) {
      scene.onShow();
      this.setCurrentScene(scene);
      this.add(scene);
    } else {
      throw new InvalidDataException("Scene not found");
    }
  }

  public <S extends Scene> S addScene(S scene) {
    this.scenes.put(scene.getClass(), scene);
    scene.setApp(this);
    return scene;
  }
}
