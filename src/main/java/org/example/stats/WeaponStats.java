package org.example.stats;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public abstract class WeaponStats extends ItemStats  {
    protected int power, mass, ammunition;
    protected boolean isReload;
}