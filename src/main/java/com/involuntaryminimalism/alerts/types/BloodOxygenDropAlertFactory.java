package com.involuntaryminimalism.alerts.types;

import com.involuntaryminimalism.alerts.Alert;

public class BloodOxygenDropAlertFactory extends AlertFactory {
  @Override
  public Alert createAlert(String patientId, String condition, long timestamp) {
    return new BloodOxygenDropAlert(patientId, condition, timestamp);
  }
}
