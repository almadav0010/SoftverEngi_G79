package com.cardio_generator.generators;

import com.Label;
import com.cardio_generator.outputs.OutputStrategy;
import java.util.Random; // deleted extra whitespace

/**
 * Used to create random test alert solutions, that can either get resolved or not, used for testing
 */
public class AlertGenerator implements PatientDataGenerator {

  public static final Random randomGenerator = new Random();
  private final boolean[] alertStates; // false = resolved, true = pressed

  // made camelCase

  /**
   * Constructor
   *
   * @param patientCount Number of patients we have as an integer
   */
  public AlertGenerator(int patientCount) {
    alertStates = new boolean[patientCount + 1];
  }

  /**
   * Simulates percentage chance of resolving issues if they are present
   *
   * @param patientId which patient do we simulate for
   * @param outputStrategy interface, based on how it outputs the generated data
   * @throws Error when something unexpected happens and we cannot generate a solution/fail
   */
  @Override
  public void generate(int patientId, OutputStrategy outputStrategy) {
    try {
      if (alertStates[patientId]) {
        if (randomGenerator.nextDouble() < 0.9) { // 90% chance to resolve
          alertStates[patientId] = false;
          // Output the alert
          outputStrategy.output(
              patientId, System.currentTimeMillis(), "Alert", "resolved"); // broke line
        }
      } else { // changed Lambda into lambda since its a var
        double lambda = 0.1; // Average rate (alerts per period),
        // adjust based on desired frequency
        double p = -Math.expm1(-lambda); // Probability of at least one alert in the period
        boolean alertTriggered = randomGenerator.nextDouble() < p;

        if (alertTriggered) {
          alertStates[patientId] = true;
          // Output the alert
          outputStrategy.output(
              patientId, System.currentTimeMillis(), Label.Alert.name(), "triggered"); // broke line
        }
      }
    } catch (Exception e) {
      System.err.println(
          "An error occurred while generating alert data for patient " + patientId); // broke line
      e.printStackTrace();
    }
  }
}
