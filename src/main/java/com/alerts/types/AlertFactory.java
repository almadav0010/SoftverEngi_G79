package com.alerts.types;

import com.alerts.Alert;

/**
 * Serves as template for creating alerts of different types via the Factory pattern to avoid
 * hardcoding alert types.
 */
public abstract class AlertFactory {
  public abstract Alert createAlert(String patientId, String condition, long timestamp);
}
