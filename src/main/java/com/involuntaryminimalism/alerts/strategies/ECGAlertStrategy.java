package com.involuntaryminimalism.alerts.strategies;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.data_management.PatientRecord;
import java.util.ArrayList;
import java.util.Optional;

public class ECGAlertStrategy implements AlertStrategy {
  private final ArrayList<PatientRecord> overTime = new ArrayList<>();

  @Override
  public Optional<String> checkAlert(PatientRecord record) {
    Label recordType = record.getRecordType();

    if (overTime.size() < 5) {
      // less than 5 previous measurement, so no good data can be derived
      overTime.add(record);
      return Optional.empty();
    }
    // skip irrelevant measurements
    if (recordType != Label.ECG) {
      return Optional.empty();
    } else {
      overTime.add(record);
    }
    double avg = 0;
    double sum = 0;
    int cnt = 0;
    for (PatientRecord pr : overTime) {
      cnt++;
      sum += pr.getMeasurementValue();
    }
    avg = sum / cnt;
    for (PatientRecord pr : overTime) {
      if (Math.abs(pr.getMeasurementValue() - avg) > (avg * 0.2)) {
        // decets 20% peaks
        return Optional.of("The patient had a peak over 20% of the average ECG");
      }
    }

    return Optional.empty();
  }
}
