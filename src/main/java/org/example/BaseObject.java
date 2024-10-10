package org.example;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseObject implements Serializable {
  private UUID uuid = UUID.randomUUID();
  private Instant createdDate = Instant.now(), updatedDate = Instant.now();
  private static final long serialVersionUID = -863164858986274318L;

  public BaseObject() {
  }
}
