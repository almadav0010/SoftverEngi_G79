package com.alerts.factories;

import com.alerts.Alert;
import com.alerts.types.TriggeredAlert;

public class TriggeredAlertFactory extends AlertFactory {
  @Override
  public Alert createAlert(String patientId, String condition, long timestamp) {
    return new TriggeredAlert();
  }
}
