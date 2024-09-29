package org.example;

import org.example.entities.Space;
import org.example.game.GameStateManager;
import org.example.input.Input;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread;
    private boolean running;
    private GameStateManager gsm;
    private Input input;  // Input object
    private Space space;

    public GamePanel() {
        this.setPreferredSize(new Dimension(800, 600));
        input = new Input();  // Initialize the input system
        gsm = new GameStateManager(input);  // Manage game states
        space = new Space();

        this.addKeyListener(input);  // Add KeyListener to the JPanel
        this.setFocusable(true);  // Ensure the JPanel can receive key inputs
        this.requestFocusInWindow();  // Focus the window for key input
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

    @Override
    public void run() {
        while (running) {
            input.update();  // Update the input state
            gsm.update();  // Update game state (including entities)
            repaint();  // Redraw the screen
            try {
                Thread.sleep(16);  // Roughly 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        space.draw(g);
        gsm.render(g);
    }
}
