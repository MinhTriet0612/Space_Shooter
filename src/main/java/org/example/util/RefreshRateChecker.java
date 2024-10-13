package org.example.util;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class RefreshRateChecker {
  public static int getCurrent() {
    GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    DisplayMode currentMode = graphicsDevice.getDisplayMode();
    return currentMode.getRefreshRate();
  }
}
