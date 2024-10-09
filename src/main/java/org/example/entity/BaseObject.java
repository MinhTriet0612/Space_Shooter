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
  private Instant createdDate = Instant.now(), updatedDate = Instant.now();
  private static final long serialVersionUID = -863164858986274318L;

  protected BaseObject() {
  }
}
