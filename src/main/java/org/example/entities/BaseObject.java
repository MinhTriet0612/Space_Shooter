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
public abstract class BaseObject implements Serializable {
    protected UUID uuid;
    protected Instant createdDate, updatedDate;
    private static final long serialVersionUID = -863164858986274318L;

    public BaseObject() {
        this.uuid = UUID.randomUUID();
        this.updatedDate = Instant.now();
        this.createdDate = Instant.now();
    }
}