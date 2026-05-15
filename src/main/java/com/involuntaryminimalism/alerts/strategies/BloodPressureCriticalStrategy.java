package com.involuntaryminimalism.alerts.strategies;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.data_management.PatientRecord;

import java.util.Optional;

/**
 * Functionality Requirements: Trend Alert: Trigger an alert if the patient's blood pressure
 * (systolic or diastolic) shows a consistent increase or decrease across three consecutive readings
 * where each reading changes by more than 10 mmHg from the last. Critical Threshold Alert: Trigger
 * an alert if the systolic blood pressure exceeds 180 mmHg or drops below 90 mmHg, or if diastolic
 * blood pressure exceeds 120 mmHg or drops below 60 mmHg.
 */
public class BloodPressureCriticalStrategy implements AlertStrategy {


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
    if (recordType == Label.SystolicPressure &&
       (bloodPressure > 180 || bloodPressure < 90)) {
        return Optional.of("Systolic blood pressure is in critical range: " + bloodPressure);
      }
    if (recordType == Label.DiastolicPressure &&
        (bloodPressure > 120 || bloodPressure < 60)) {
        return Optional.of("Diastolic blood pressure is in critical range: " + bloodPressure);
      }

    return Optional.empty();
  }

}
