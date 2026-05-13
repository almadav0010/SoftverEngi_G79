package com.cardio_generator.generators;

import com.Label;
import com.cardio_generator.outputs.OutputStrategy;
import java.util.Random;

/** Generates random Blood saturation levels for patients */
public class BloodSaturationDataGenerator implements PatientDataGenerator {
  private static final Random random = new Random();
  private final int[] lastSaturationValues;

  /**
   * Initializes the lastSaturationValues from 95 to 100 for each patient
   *
   * @param patientCount number of patients in integer
   */
  public BloodSaturationDataGenerator(int patientCount) {
    lastSaturationValues = new int[patientCount + 1];

    // Initialize with baseline saturation values for each patient
    for (int i = 1; i <= patientCount; i++) {
      lastSaturationValues[i] =
          95 + random.nextInt(6); // Initializes with a value between 95 and 100
    }
  }

  /**
   * Nudges the saturation of the input patient within 90 and 100
   *
   * @param patientId the patient whose data is to be changed
   * @param outputStrategy the way and wehre to output the data
   */
  @Override
  public void generate(int patientId, OutputStrategy outputStrategy) {
    try {
      // Simulate blood saturation values
      int variation = random.nextInt(3) - 1; // -1, 0, or 1 to simulate small fluctuations
      int newSaturationValue = lastSaturationValues[patientId] + variation;

      // Ensure the saturation stays within a realistic and healthy range
      newSaturationValue = Math.min(Math.max(newSaturationValue, 90), 100);
      lastSaturationValues[patientId] = newSaturationValue;
      outputStrategy.output(
          patientId,
          System.currentTimeMillis(),
          Label.Saturation.name(),
          Double.toString(newSaturationValue) + "%");
    } catch (Exception e) {
      System.err.println(
          "An error occurred while generating blood saturation data for patient " + patientId);
      e.printStackTrace(); // This will print the stack trace to help identify where the error
      // occurred.
    }
  }
}
