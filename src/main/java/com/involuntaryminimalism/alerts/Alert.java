package com.involuntaryminimalism.alerts;

// Represents an alert
public interface Alert {

  String getPatientId();

  String getCondition();

  long getTimestamp();
}
