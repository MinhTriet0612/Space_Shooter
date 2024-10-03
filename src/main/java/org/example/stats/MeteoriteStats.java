package org.example.stats;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MeteoriteStats extends EntityStats { 
    private final int critRate = 100;
    private int critDame;
}