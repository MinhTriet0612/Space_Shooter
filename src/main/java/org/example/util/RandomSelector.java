package org.example.util;

import java.util.Random;

public class RandomSelector {

  // Method to randomly select an item based on probabilities
  public static <T> T random(T[] items, double[] probabilities) {
    if (items == null || probabilities == null || items.length != probabilities.length + 1) {
      throw new IllegalArgumentException("The probabilities array must be one less than the items array.");
    }

    // Calculate the probability for the last item
    double totalProbability = 0.0;
    for (double prob : probabilities) {
      totalProbability += prob;
    }
    if (totalProbability >= 1.0) {
      throw new IllegalArgumentException("Sum of specified probabilities must be less than 1.");
    }

    // Add the probability of the last item to the list
    double[] fullProbabilities = new double[items.length];
    System.arraycopy(probabilities, 0, fullProbabilities, 0, probabilities.length);
    fullProbabilities[items.length - 1] = 1.0 - totalProbability;

    // Generate a random value between 0 and 1
    Random random = new Random();
    double randomValue = random.nextDouble();

    // Select an item based on the generated random value
    double cumulativeProbability = 0.0;
    for (int i = 0; i < fullProbabilities.length; i++) {
      cumulativeProbability += fullProbabilities[i];
      if (randomValue <= cumulativeProbability) {
        return items[i];
      }
    }
    return items[items.length - 1]; // Fallback, should not reach here
  }
}
