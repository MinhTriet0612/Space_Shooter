package org.example.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipControlInput implements KeyListener {

    private boolean[] keys;
    private boolean up, down, left, right, space;

    public ShipControlInput() {
        keys = new boolean[256];
        this.down = false;
        this.up = false;
        this.left = false;
        this.right = false;
        this.space = false;
    }

    public void update() {
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keys[e.getKeyCode()] = false;
    }
}
