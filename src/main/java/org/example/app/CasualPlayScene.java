package org.example.app;


import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import org.example.builder.ShipBuilder;
import org.example.common.ScreenAttributeConstant;
import org.example.entities.Ship;
import org.example.input.ShipControlInput;
import org.example.system.GameSystem;
import org.example.system.controller.MonsterSponsor;
import org.example.system.controller.ShipController;
import org.example.world.CasualWorld;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CasualPlayScene extends Scene {
    private Boolean isRunning;
    private CasualWorld world;
    private final Thread gameThread;
    private final ShipControlInput shipCtrlInput;
    private final CasualPlaySceneBackground casualPlaySceneBackground;

    public CasualPlayScene() {
        this.isRunning = true;
        this.gameThread = new Thread(this);
        this.shipCtrlInput = new ShipControlInput();
        this.setPreferredSize(
            new Dimension(
                ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH,
                ScreenAttributeConstant.CASUALPLAYSCENE_HEIGHT)
        );
        this.casualPlaySceneBackground = new CasualPlaySceneBackground();

        this.world = new CasualWorld();

        Ship playerShip = ShipBuilder.shipBuilder();
        List<GameSystem> systemInits = List.of(
            new MonsterSponsor(),
            new ShipController(playerShip, shipCtrlInput)
        );
        
        systemInits.forEach(system -> {
            system.setWorld(world);
            if (system instanceof ShipController shipController) {
                shipController.setShip(playerShip);
                shipController.getShip().setWorld(world);
            }
        });

        this.world.getEntities().add(playerShip);
        this.world.getSystems().addAll(systemInits);

        this.setFocusable(true);
        this.addKeyListener(shipCtrlInput);
    }

    @Override
    public void onShow() {
        this.gameThread.start();
    }

    @Override
    public void run() {
        while (isRunning) {
            this.shipCtrlInput.update();
            this.world.update(1f);
            repaint();
            try {
                Thread.sleep(16);
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
        this.isRunning = false;
        this.gameThread.interrupt();
        this.setFocusable(true); 
    }

    @Override
    public void update(float deltaTime) {
    }
}