package org.example.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity implements Serializable { // Saving Game State, Persisting Sessions, Realtime Across Networking
    private UUID id;
    private Boolean isDeleted;
    private Instant createdDate;
    private Instant updatedDate;
    private static final long serialVersionUID = -863164858986274318L;

    public BaseEntity() {
        this.isDeleted = false;
        this.updatedDate = null;
        this.id = UUID.randomUUID();
        this.createdDate = Instant.now();
    }
}