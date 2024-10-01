package org.example;

import javax.swing.*;

public class Main {

    private static JFrame frame = new JFrame("Space Shooter Game");
    private static GamePanel gamePanel = new GamePanel();

    public static void main(String[] args) {
        try {
            frame.pack();
            gamePanel.start();
            frame.add(gamePanel);
            frame.setVisible(true);
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
