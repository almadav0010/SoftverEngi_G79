package com.involuntaryminimalism.alerts.types;

import com.involuntaryminimalism.alerts.Alert;

public class BloodOxygenAlert implements Alert {
  private final String patientId;
  private final String condition;
  private final long timestamp;

  protected BloodOxygenAlert(String patientId, String condition, long timestamp) {
    this.patientId = patientId;
    this.condition = condition;
    this.timestamp = timestamp;
  }

  public String getPatientId() {
    return patientId;
  }

  public String getCondition() {
    return condition;
  }

  public long getTimestamp() {
    return timestamp;
  }
}
