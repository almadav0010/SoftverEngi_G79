package com.involuntaryminimalism.alerts.strategies;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.data_management.PatientRecord;
import java.util.Optional;

public class TriggeredAlertStrategy implements AlertStrategy {
  @Override
  public Optional<String> checkAlert(PatientRecord record) {
    Label recordType = record.getRecordType();

    // skip irrelevant measurements
    if (recordType != Label.Alert) {
      return Optional.empty();
    }
    return Optional.of("The patient pressed the alert button");
  }
}
