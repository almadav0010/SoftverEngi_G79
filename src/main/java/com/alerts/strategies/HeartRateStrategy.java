package com.alerts.strategies;

import com.data_management.PatientRecord;
import java.util.Optional;

public class HeartRateStrategy implements AlertStrategy {

  @Override
  public Optional<String> checkAlert(PatientRecord record) {
    return Optional.empty();
  }
}
