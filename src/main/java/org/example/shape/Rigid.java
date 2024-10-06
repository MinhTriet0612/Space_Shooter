package org.example.shape;

import org.example.entities.BaseObject;
import org.example.util.Vector2D;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Rigid extends BaseObject {
    protected Vector2D position;
}