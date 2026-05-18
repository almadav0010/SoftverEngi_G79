package com.involuntaryminimalism.alerts.types;

import com.involuntaryminimalism.alerts.Alert;

public class HypotensiveHypoxemiaAlertFactory extends AlertFactory {
  @Override
  public Alert createAlert(String patientId, String condition, long timestamp) {
    return new HypotensiveHypoxemiaAlert(patientId,condition,timestamp);
  }
}
