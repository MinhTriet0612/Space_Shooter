package org.example.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;

public class GraphicsUtils {

  public enum DrawMode {
    CENTER, NORMAL
  }

  // Method to draw an ellipse
  public static void drawEllipse(Graphics g, int x, int y, int width, int height, Color color, DrawMode mode) {
    if (mode == DrawMode.CENTER) {
      x -= width / 2;
      y -= height / 2;
    }
    g.setColor(color);
    g.drawOval(x, y, width, height);
  }

  // Method to draw a rectangle
  public static void drawRectangle(Graphics g, int x, int y, int width, int height, Color color, DrawMode mode) {
    if (mode == DrawMode.CENTER) {
      x -= width / 2;
      y -= height / 2;
    }
    g.setColor(color);
    g.drawRect(x, y, width, height);
  }

  // Method to draw a polygon
  public static void drawPolygon(Graphics g, int[] xPoints, int[] yPoints, Color color, DrawMode mode) {
    if (mode == DrawMode.CENTER) {
      int centerX = findAverage(xPoints);
      int centerY = findAverage(yPoints);

      for (int i = 0; i < xPoints.length; i++) {
        xPoints[i] -= centerX;
        yPoints[i] -= centerY;
      }
    }
    g.setColor(color);
    g.drawPolygon(new Polygon(xPoints, yPoints, xPoints.length));
  }

  // Method to draw an image
  public static void drawImage(Graphics g, Image img, int x, int y, int width, int height, DrawMode mode) {
    if (mode == DrawMode.CENTER) {
      x -= width / 2;
      y -= height / 2;
    }
    g.drawImage(img, x, y, width, height, null);
  }

  // Utility method to find the average of an array of integers (for centering
  // polygons)
  private static int findAverage(int[] values) {
    int sum = 0;
    for (int value : values) {
      sum += value;
    }
    return values.length > 0 ? sum / values.length : 0;
  }
}
