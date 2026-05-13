package com.alerts.strategies;

import com.data_management.Patient;
import com.data_management.PatientRecord;

import java.util.Optional;

public class HeartRateStrategy implements AlertStrategy {

  @Override
  public long lastProcessedTimeStamp() {
    return 0;
  }

  @Override
  public Optional<String> checkAlert(Patient patient) {
    return Optional.empty();
  }
}
