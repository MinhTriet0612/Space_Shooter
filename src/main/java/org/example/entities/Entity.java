package org.example.entities;

import java.awt.Graphics;

import org.example.shape.Rigid;
import org.example.stats.EntityStats;
import org.example.system.status.Status;
import org.example.util.Response;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class Entity<S extends EntityStats> extends BaseObject {
    protected Rigid rigid;
    protected Status<S> status;
    protected Vector2D position;

    public abstract void render(Graphics g);

    public abstract void update(float deltaTime);

    public abstract void onCollisionStay(Entity other, Response response);

    public abstract void onCollisionExit(Entity other, Response response);

    public abstract void onCollisionEnter(Entity other, Response response);
}