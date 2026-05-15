package com.involuntaryminimalism.alerts.types;

import com.involuntaryminimalism.alerts.Alert;

public class BloodPressureCriticalAlertFactory extends AlertFactory {
  @Override
  public Alert createAlert(String patientId, String condition, long timestamp) {
    return new BloodPressureCriticalAlert(patientId, condition, timestamp);
  }
}
