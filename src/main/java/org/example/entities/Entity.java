package org.example.entities;

import java.awt.*;

import org.example.shape.Rigid;
import org.example.status.Status;
import org.example.util.Response;
import org.example.util.Vector2D;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Entity<T extends Status> extends BaseEntity {
    protected T status;    
    protected Rigid rigid;
    protected Vector2D position;

    public abstract void render(Graphics g);
    public abstract void update(float deltaTime);
    public abstract void onCollisionStay(Entity<T> other, Response response);
    public abstract void onCollisionExit(Entity<T> other, Response response);    
    public abstract void onCollisionEnter(Entity<T> other, Response response);
}