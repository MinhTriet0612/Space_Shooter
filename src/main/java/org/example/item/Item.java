package org.example.item;

import org.example.entities.BaseObject;
import org.example.stats.ItemStats;
import org.example.stats.LazerGunStats;
import org.example.system.status.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder; 

@Setter
@Getter
@SuperBuilder
public abstract class Item<T extends ItemStats> extends BaseObject {
    protected Status<T> status;
    public abstract void update(float deltaTime);
}