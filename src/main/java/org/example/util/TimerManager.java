package org.example.util;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TimerManager {
  private static final Set<Timer> timers = new HashSet<>();

  public static Timer createTimer(int delay, ActionListener listener) {
    Timer timer = new Timer(delay, listener);

    // Add to the activeTimers set when the timer starts
    timer.addActionListener(e -> {
      if (!timers.contains(timer) && timer.isRunning()) {
        timers.add(timer);
      }
    });

    // Remove from the activeTimers set when the timer stops
    timer.addActionListener(e -> {
      if (!timer.isRunning()) {
        timers.remove(timer);
      }
    });

    return timer;
  }

  public static int getActiveTimerCount() {
    // Clean up the set by checking each timer's status
    Iterator<Timer> iterator = timers.iterator();
    int count = 0;
    while (iterator.hasNext()) {
      Timer timer = iterator.next();
      if (timer.isRunning()) {
        count++;
      }
    }

    return count;
  }
}
