package org.example.system;

import org.example.world.World;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GameSystem {
    protected World world;
    public abstract void update(float deltaTime);
}