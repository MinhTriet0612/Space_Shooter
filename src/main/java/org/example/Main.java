package org.example;

import org.example.entities.Ship;
import org.example.game.GameStateManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("Hello World");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GamePanel gamePanel = new GamePanel();
            gamePanel.start();
            frame.add(gamePanel);
            frame.pack();
            frame.setSize(800, 700);
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }


    }

}
