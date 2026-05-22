package com.involuntaryminimalism.alerts.strategies;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.data_management.PatientRecord;
import java.util.Optional;

public class HyHyStrategy implements AlertStrategy {
  private PatientRecord lastRecord = null;

  // some weird java stuff, basically comparator tells it based on what to sort

  @Override
  public Optional<String> checkAlert(PatientRecord record) {
    Label recordType = record.getRecordType();

    // skip irrelevant measurements
    if (recordType != Label.Saturation && recordType != Label.SystolicPressure) {
      return Optional.empty();
    } else if (lastRecord == null) {
      lastRecord = record;
    } else {
      double saturation;
      double pressure;

      if (lastRecord.getTimestamp() == record.getTimestamp()
          && lastRecord.getRecordType() != record.getRecordType()) {
        // if they are different saved measurements at the same time
        if (lastRecord.getRecordType() == Label.Saturation) {
          saturation = lastRecord.getMeasurementValue();
          pressure = record.getMeasurementValue();
        } else {
          pressure = lastRecord.getMeasurementValue();
          saturation = record.getMeasurementValue();
        }
        if (pressure < 90 && saturation < 92) {
          return Optional.of(
              "The patient is experiencing Hypotensive Hypoxemia, with pressure of: "
                  + pressure
                  + ", and saturation of: "
                  + saturation);
        }
      } else {
        lastRecord = record;
      }
    }
    return Optional.empty();
  }
}
