package com.involuntaryminimalism.alerts.types;

import com.involuntaryminimalism.alerts.Alert;

public class BloodPressureTrendAlertFactory extends AlertFactory {
  @Override
  public Alert createAlert(String patientId, String condition, long timestamp) {
    return new BloodPressureTrendAlert(patientId, condition, timestamp);
  }
}
