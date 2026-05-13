package com.alerts.types;

import com.alerts.Alert;

public class HypotensiveHypoxemiaAlertFactory extends AlertFactory {
  @Override
  public Alert createAlert(String patientId, String condition, long timestamp) {
    return new HypotensiveHypoxemiaAlert();
  }
}
