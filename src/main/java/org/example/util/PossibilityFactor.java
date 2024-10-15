package org.example.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PossibilityFactor {
  private List<Integer> possibilities = new ArrayList<>();
  private Random random = new Random();

  public static interface Item {
    public int getValue();
  }

  public PossibilityFactor() {
  }

  public void setFactor(List<Integer> items) {
    this.possibilities.clear();
    this.possibilities.addAll(items);
  }

  // Method to add an integer to the possibilities list
  public void add(int item) {
    this.possibilities.add(item);
  }

  // Method to remove an integer from the possibilities list
  public boolean remove(int item) {
    return this.possibilities.remove(Integer.valueOf(item));
  }

  // Method to get a random number based on the set possibilities
  public int random() {
    if (possibilities.isEmpty()) {
      throw new IllegalStateException("Possibilities list is empty. Please set possibilities first.");
    }
    int randomIndex = random.nextInt(possibilities.size());
    return possibilities.get(randomIndex);
  }
}
