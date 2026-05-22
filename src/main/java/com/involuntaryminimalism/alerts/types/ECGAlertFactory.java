package com.involuntaryminimalism.alerts.types;

import com.involuntaryminimalism.alerts.Alert;

public class ECGAlertFactory extends AlertFactory {
  @Override
  public Alert createAlert(String patientId, String condition, long timestamp) {
    return new ECGAlert(patientId, condition, timestamp);
  }
}
