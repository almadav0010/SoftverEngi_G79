package com.alerts.strategies;

import com.data_management.PatientRecord;

public class BloodPressureStrategy implements AlertStrategy {
  @Override
  public boolean checkAlert(PatientRecord patientRecord) {
    return false;
  }
}
