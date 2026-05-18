package com.involuntaryminimalism.alerts.types;

import com.involuntaryminimalism.alerts.Alert;

/**
 * This alert type, called the "Hypotensive Hypoxemia Alert," will trigger based on combined low
 * blood pressure and low blood oxygen saturation levels. This condition is particularly dangerous
 * because it indicates potential organ damage or severe infection risks, among other serious health
 * concerns.
 */
public class HypotensiveHypoxemiaAlert implements Alert {
  private final String patientId;
  private final String condition;
  private final long timestamp;

  protected HypotensiveHypoxemiaAlert(String patientId, String condition, long timestamp) {
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
