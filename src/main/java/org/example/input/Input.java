package org.example.input;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
    private boolean[] keys;  // Store the states of all keys
    public boolean up, down, left, right, space;  // Movement flags


    public Input()
    {
        keys = new boolean[256];
    }

    public void update()
    {
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
        update();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.keys[e.getKeyCode()] = false;
        update();
    }
}
