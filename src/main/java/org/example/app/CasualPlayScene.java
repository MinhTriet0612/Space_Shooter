package org.example.app;


import java.awt.Dimension;
import java.awt.Graphics;

import org.example.builder.MonsterBuilder;
import org.example.builder.ShipBuilder;
import org.example.entities.Monster;
import org.example.entities.Ship;
import org.example.input.ShipControlInput;
import org.example.system.GameSystem;
import org.example.system.controller.ShipController;
import org.example.world.CasualWorld;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CasualPlayScene extends Scene {
    private boolean running;
    private CasualWorld world;
    private ShipControlInput shipControlInput;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private Thread gameThread = new Thread(this);
    private CasualPlaySceneBackground casualPlaySceneBackground;

    public CasualPlayScene() {
        this.shipControlInput = new ShipControlInput();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.casualPlaySceneBackground = new CasualPlaySceneBackground();

        Ship ship = ShipBuilder.shipBuilder();
        this.world = new CasualWorld();
        GameSystem system = new ShipController(ship, shipControlInput);


        for(int i = 0; i < WIDTH; i += 100) {
            int y = (int) Math.round(Math.random()*i);
            Monster monster = MonsterBuilder.monsterBuilder(i, -1*y);
            this.world.getEntities().add(monster);
        }
        this.world.getSystems().add(system);



        this.addKeyListener(shipControlInput);
        this.setFocusable(true); 
    }

    @Override
    public void onShow() {
        this.gameThread.start();
        running = true;
    }

    @Override
    public void run() {
        while (running) {
            this.shipControlInput.update();
            this.world.update(1f);
            repaint();
            try {
                Thread.sleep(13);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.casualPlaySceneBackground.draw(g);
        this.world.render(g);
    }

    @Override
    public void inDispose() {
        // gameThread.interrupt();
        // running = false;
    }

    @Override
    public void update(float deltaTime) {
        // // throw new UnsupportedOperationException("Not supported yet.");
    }
}