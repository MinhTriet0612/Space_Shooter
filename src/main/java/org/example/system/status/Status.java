package org.example.system.status;

import java.util.ArrayList;
import java.util.List;

import org.example.stats.Stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Status<T extends Stats> {
    private T currentStast;
    private final T initStast;
    private List<Effect> effects;

    public Status(T stats) {
        this.initStast = stats;
        this.currentStast = stats;
        effects = new ArrayList<>();
    }
}