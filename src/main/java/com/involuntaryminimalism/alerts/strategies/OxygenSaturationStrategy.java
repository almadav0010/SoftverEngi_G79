package com.involuntaryminimalism.alerts.strategies;

import com.involuntaryminimalism.data_management.PatientRecord;
import java.util.Optional;

public class OxygenSaturationStrategy implements AlertStrategy {
  @Override
  public Optional<String> checkAlert(PatientRecord record) {
    return Optional.empty();
  }
}
