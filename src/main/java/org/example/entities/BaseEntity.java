package org.example.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
// Saving Game State, Persisting Sessions, Realtime Across Networking
public class BaseEntity implements Serializable { 
    private UUID id;
    private Boolean isDeleted;
    private Instant createdDate, updatedDate;
    private static final long serialVersionUID = -863164858986274318L;

    public BaseEntity() {
        this.isDeleted = false;
        this.updatedDate = null;
        this.id = UUID.randomUUID();
        this.createdDate = Instant.now();
    }
}