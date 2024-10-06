package org.example.system.status;

import java.util.ArrayList;
import java.util.List;

import org.example.stats.Stats;
import org.example.util.DeepCopyUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Status<T extends Stats> {
    private T initStast;
    private T currentStast;
    private List<Effect> effects;

    public Status(T stats) {
        this.effects = new ArrayList<>();
        this.initStast = DeepCopyUtils.copy(stats);
        this.currentStast = DeepCopyUtils.copy(stats);
    }
}