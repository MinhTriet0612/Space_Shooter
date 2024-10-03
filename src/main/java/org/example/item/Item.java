package org.example.item;

import org.example.entities.BaseEntity;
import org.example.stats.ItemStats;
import org.example.stats.Stats;
import org.example.system.status.Status;

import lombok.Getter;
import lombok.Setter; 

@Setter
@Getter
public abstract class Item extends BaseEntity {
    protected Status<ItemStats> status;
    public abstract void update(float deltaTime);
}