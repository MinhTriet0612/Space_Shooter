package org.example.app; 

import javax.swing.JPanel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Scene extends JPanel implements Runnable {
    protected App app;
    public abstract void onShow();
    public abstract void inDispose();
    public abstract void update(float deltaTime);
}