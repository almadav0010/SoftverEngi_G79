package com.alerts.types;

import com.alerts.Alert;

public class BloodOxygenAlert implements Alert {
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
