package com.alerts.strategies;

import com.Label;
import com.data_management.PatientRecord;
import java.util.Map;
import java.util.Optional;

/**
 * Functionality Requirements: Trend Alert: Trigger an alert if the patient's blood pressure
 * (systolic or diastolic) shows a consistent increase or decrease across three consecutive readings
 * where each reading changes by more than 10 mmHg from the last. Critical Threshold Alert: Trigger
 * an alert if the systolic blood pressure exceeds 180 mmHg or drops below 90 mmHg, or if diastolic
 * blood pressure exceeds 120 mmHg or drops below 60 mmHg.
 */
public class BloodPressureStrategy implements AlertStrategy {

  private final Map<Integer, Integer> consecutiveDropsDia = Map.of();
  private final Map<Integer, Double> lastBloodPressureDia = Map.of();

  private final Map<Integer, Integer> consecutiveDropsSys = Map.of();
  private final Map<Integer, Double> lastBloodPressureSys = Map.of();

  /**
   * Check blood pressure alert conditions for a new patient record assuming the records are passed
   * to this method in chronological order.
   *
   * @param record to be checked if it requires an alert to be formed
   * @return a string with the condition of the alert to be triggered. if no alert needs to be
   *     triggered, the optional is empty.
   */
  @Override
  public Optional<String> checkAlert(PatientRecord record) {
    Label recordType = record.getRecordType();

    // skip irrelevant measurements
    if (recordType != Label.SystolicPressure && recordType != Label.DiastolicPressure) {
      return Optional.empty();
    }

    // at this point we know it is blood pressure
    double bloodPressure = record.getMeasurementValue();

    int patientId = record.getPatientId();
    int THRESHOLD_AMOUNT = 3;

    // CRITICAL THRESHOLD ALERT
    if (recordType == Label.SystolicPressure) {
      if (bloodPressure > 180 || bloodPressure < 90) {
        return Optional.of("Systolic blood pressure is in critical range: " + bloodPressure);
      }
      updateTrendTrackingMaps(consecutiveDropsSys, lastBloodPressureSys, patientId, bloodPressure);
      int consecutiveJumps = consecutiveDropsSys.get(patientId);
      if (Math.abs(consecutiveJumps) >= THRESHOLD_AMOUNT) {
        return Optional.of(
            "Systolic blood pressure shows a consistent increase or decrease across at least "
                + "three consecutive readings where each reading changes by more than 10 mmHg from "
                + "the last.");
      }
    }
    if (recordType == Label.DiastolicPressure) {
      if (bloodPressure > 120 || bloodPressure < 60) {
        return Optional.of("Diastolic blood pressure is in critical range: " + bloodPressure);
      }

      updateTrendTrackingMaps(consecutiveDropsDia, lastBloodPressureDia, patientId, bloodPressure);
      int consecutiveJumps = consecutiveDropsSys.get(patientId);
      if (Math.abs(consecutiveJumps) >= THRESHOLD_AMOUNT) {
        return Optional.of(
            "Diastolic blood pressure shows a consistent increase or decrease across at least "
                + "three consecutive readings where each reading changes by more than 10 mmHg from "
                + "the last.");
      }
    }

    return Optional.empty();
  }

  private void updateTrendTrackingMaps(
      Map<Integer, Integer> consecutiveCounterMap,
      Map<Integer, Double> lastMeasurementMap,
      int patientId,
      double currentMeasurement) {
    Double lastMeasurement;

    // if there is a previous measurement, check if trend present
    if ((lastMeasurement = lastBloodPressureSys.get(patientId)) != null) {
      int direction = consecutiveDropsSys.getOrDefault(patientId, 0);

      // previous upward trend
      if (direction > 0) {
        // previous trend sustained
        if (currentMeasurement - lastMeasurement > 10) {
          consecutiveDropsSys.put(patientId, direction + 1);
        }
        // previous trend broken -> reset
        else {
          consecutiveDropsSys.put(patientId, 0);
        }
      }
      // previous downward trend
      else if (direction < 0 && currentMeasurement - lastMeasurement < -10) {
        // previous trend sustained
        if (currentMeasurement - lastMeasurement < -10) {
          consecutiveDropsSys.put(patientId, direction - 1);
        }
        // previous trend broken -> reset
        else {
          consecutiveDropsSys.put(patientId, 0);
        }
      }
      // no trend
      else {
        consecutiveDropsSys.put(patientId, 0);
      }
    }
    // in case no previous measurement, just register the first one incoming
    else {
      lastBloodPressureSys.put(patientId, currentMeasurement);
      consecutiveDropsSys.put(patientId, 0);
    }
  }
}
