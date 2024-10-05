package org.example.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import org.example.world.World;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class BaseEntity implements Serializable, Cloneable {
    protected UUID uuid;
    protected World world;
    protected Instant createdDate, updatedDate;
    private static final long serialVersionUID = -863164858986274318L;

    public BaseEntity() {
        this.uuid = UUID.randomUUID();
        this.updatedDate = Instant.now();
        this.createdDate = Instant.now();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}