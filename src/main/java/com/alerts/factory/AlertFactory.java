package com.alerts.factory;

import com.alerts.Alert;

/**
 * Serves as template for creating alerts of different types via the Factory pattern to avoid
 * hardcoding alert types.
 */
public class AlertFactory {
  public static Alert createAlert(String patientId, String condition, long timestamp) {return null;}
}
