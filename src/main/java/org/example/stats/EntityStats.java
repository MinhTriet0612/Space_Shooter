package org.example.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EntityStats extends Stats {
    protected int health;
}