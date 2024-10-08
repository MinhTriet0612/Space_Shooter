package org.example.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseObject implements Serializable {
  private UUID uuid = UUID.randomUUID();
  private Instant createdDate, updatedDate;
  private static final long serialVersionUID = -863164858986274318L;

  public BaseObject() {
    this.uuid = UUID.randomUUID();
    this.updatedDate = Instant.now();
    this.createdDate = Instant.now();
  }
}
