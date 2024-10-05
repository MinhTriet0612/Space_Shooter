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
public class BaseEntity implements Serializable {
    private UUID id;
    private Instant createdDate, updatedDate;
    private static final long serialVersionUID = -863164858986274318L;

    public BaseEntity() {
        this.id = UUID.randomUUID();
        this.updatedDate = Instant.now();
        this.createdDate = Instant.now();
    }
}