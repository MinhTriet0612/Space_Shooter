package org.example.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class MortalEntityStatus extends EntityStatus  {
    protected int health;
}