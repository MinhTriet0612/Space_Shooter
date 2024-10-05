package org.example.item;

import org.example.entities.BaseEntity;
import org.example.stats.ItemStats;
import org.example.system.status.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder; 

@Setter
@Getter
@SuperBuilder
public abstract class Item<T extends ItemStats> extends BaseEntity {
    protected Status<T> status;
    public abstract void update(float deltaTime);
}