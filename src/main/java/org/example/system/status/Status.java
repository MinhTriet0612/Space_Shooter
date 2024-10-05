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
    private T currentStast;
    private T initStast;
    private List<Effect> effects;

    public Status(T stats) {
        this.initStast = DeepCopyUtils.copy(stats);
        this.currentStast = DeepCopyUtils.copy(stats);
        this.effects = new ArrayList<>();
    }
}