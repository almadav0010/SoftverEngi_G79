package com.involuntaryminimalism.alerts.types;

import com.involuntaryminimalism.alerts.Alert;

/**
 * This alert type, called the "Hypotensive Hypoxemia Alert," will trigger based on combined low
 * blood pressure and low blood oxygen saturation levels. This condition is particularly dangerous
 * because it indicates potential organ damage or severe infection risks, among other serious health
 * concerns.
 */
public class HypotensiveHypoxemiaAlert implements Alert {
  @Override
  public String getPatientId() {
    return "";
  }

  @Override
  public String getCondition() {
    return "";
  }

  @Override
  public long getTimestamp() {
    return 0;
  }
}
