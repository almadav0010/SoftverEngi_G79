package com.involuntaryminimalism.alerts.strategies;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.data_management.PatientRecord;
import java.util.Optional;

public class BloodOxygenStrategy implements AlertStrategy {
  @Override
  public Optional<String> checkAlert(PatientRecord record) {
    Label recordType = record.getRecordType();

    // skip irrelevant measurements
    if (recordType != Label.Saturation) {
      return Optional.empty();
    }
    if (record.getMeasurementValue() < 92) { // stored in percentage not fraction
      return Optional.of(
          "Blood oxygen sturation below 92%! Measured value: " + record.getMeasurementValue());
    }
    return Optional.empty();
  }
}
