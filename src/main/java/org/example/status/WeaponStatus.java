package org.example.status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class WeaponStatus extends ItemStatus  {
    protected int power, mass;
}