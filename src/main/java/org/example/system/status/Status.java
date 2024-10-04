package org.example.system.status;

import java.util.ArrayList;
import java.util.List;

import org.example.stats.Stats;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// @SuperBuilder
public class Status<T extends Stats> {
    private T currentStast;
    private T initStast;
    private List<Effect> effects;

    public Status(T stats) {
        this.initStast = stats;
        this.currentStast = stats;
        this.effects = new ArrayList<>();
    }
}