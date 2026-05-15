package com.involuntaryminimalism.alerts.strategies;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.data_management.PatientRecord;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BloodPressureTrendStrategy implements AlertStrategy {
  private final Map<Integer, Integer> consecutiveDropsDia = new HashMap<>();
  private final Map<Integer, Double> lastBloodPressureDia = new HashMap<>();

  private final Map<Integer, Integer> consecutiveDropsSys = new HashMap<>();
  private final Map<Integer, Double> lastBloodPressureSys = new HashMap<>();

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
      updateTrendTrackingMaps(consecutiveDropsSys, lastBloodPressureSys, patientId, bloodPressure);
      int consecutiveJumps = consecutiveDropsSys.getOrDefault(patientId, 0);
      System.out.println("From systolic: " + consecutiveJumps);
      if (Math.abs(consecutiveJumps) >= THRESHOLD_AMOUNT-1) {
        return Optional.of(
            "Systolic blood pressure shows a consistent increase or decrease across at least "
                + "three consecutive readings where each reading changes by more than 10 mmHg from "
                + "the last.");
      }
    }
    if (recordType == Label.DiastolicPressure) {
      updateTrendTrackingMaps(consecutiveDropsDia, lastBloodPressureDia, patientId, bloodPressure);
      int consecutiveJumps = consecutiveDropsDia.getOrDefault(patientId, 0);
      System.out.println("From diastolic: " + consecutiveJumps);
      if (Math.abs(consecutiveJumps) >= THRESHOLD_AMOUNT-1) {
        return Optional.of(
            "Diastolic blood pressure shows a consistent increase or decrease across at least "
                + "three consecutive readings where each reading changes by more than 10 mmHg from "
                + "the last.");
      }
    }

    return Optional.empty();
  }

  /**
   * To avoid code duplication, this is a helper function for
   * lastBloodPressureXXX and consecutiveDropsXXX to update them
   * when processing a new measurement.**/
  private void updateTrendTrackingMaps(
      Map<Integer, Integer> consecutiveCounterMap,
      Map<Integer, Double> lastMeasurementMap,
      int patientId,
      double currentMeasurement) {
    Double lastMeasurement = lastMeasurementMap.get(patientId);

    // if there is a previous measurement, check if trend present
    if (lastMeasurement != null) {
      int direction = consecutiveCounterMap.getOrDefault(patientId, 0);
      double delta = currentMeasurement - lastMeasurement;

      if (delta > 10) {
        if (direction >= 0) {
          consecutiveCounterMap.put(patientId, direction + 1);
        } else {
          consecutiveCounterMap.put(patientId, -1);
        }
      } else if (delta < -10) {
        if (direction <= 0) {
          consecutiveCounterMap.put(patientId, direction - 1);
        } else {
          consecutiveCounterMap.put(patientId, -1);
        }
      } else {
        consecutiveCounterMap.put(patientId, 0);
      }
    }
    // in case no previous measurement, reset
    else {
      consecutiveCounterMap.put(patientId, 0);
    }

    // at the end update the last measurement
    lastMeasurementMap.put(patientId, currentMeasurement);
  }
}
