package org.example.stats;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class WeaponStats extends ItemStats  {
    protected int power, mass;
}