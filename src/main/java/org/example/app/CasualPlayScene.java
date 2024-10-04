package org.example.app;


import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import org.example.builder.ShipBuilder;
import org.example.common.ScreenAttributeConstant;
import org.example.entities.Ship;
import org.example.input.ShipControlInput;
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
    private CasualPlaySceneBackground casualPlaySceneBackground;

    public CasualPlayScene() {
        this.isRunning = true;
        this.setPreferredSize(
            new Dimension(
                ScreenAttributeConstant.CASUALPLAYSCENE_WIDTH, 
                ScreenAttributeConstant.CASUALPLAYSCENE_HEIGHT)
        );
        this.casualPlaySceneBackground = new CasualPlaySceneBackground();
        this.gameThread = new Thread(this);
        this.shipCtrlInput = new ShipControlInput();

        this.world = new CasualWorld();
        Ship playerShip = ShipBuilder.shipBuilder();
        MonsterSponsor monsterSponsor = new MonsterSponsor();
        ShipController shipController = new ShipController(playerShip, shipCtrlInput);
        
        monsterSponsor.setWorld(world);
        this.world.getEntities().add(playerShip);
        this.world.getSystems().addAll(List.of(shipController, monsterSponsor));

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
                Thread.sleep(15);
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
        gameThread.interrupt();
        this.setFocusable(true); 
    }

    @Override
    public void update(float deltaTime) {
    }
}