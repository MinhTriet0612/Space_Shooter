package org.example;

import org.example.entities.Space;
import org.example.game.GameStateManager;
import org.example.input.Input;
import org.example.status.BulletStatus;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread;
    private boolean running;
    private GameStateManager gsm;
    private Input input;  // Input object
    private Space space;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    BulletStatus x = new BulletStatus("x");

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setDoubleBuffered(true); // Draw animation smoother than
        this.setFocusable(true);  // Notice input from keyboard
        input = new Input();  // Initialize the input system
        gsm = new GameStateManager(input);  // Manage game states
        space = new Space();

        this.addKeyListener(input);  // Add KeyListener to the JPanel
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
            } catch (Exception e) {
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
