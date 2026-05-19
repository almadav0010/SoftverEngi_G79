package com.involuntaryminimalism.alerts.decorators;

import com.involuntaryminimalism.alerts.Alert;

public class AlertDecorator implements Alert {
  Alert wrappee;

  public AlertDecorator(Alert wrappee) {
    this.wrappee = wrappee;
  }

  @Override
  public String getPatientId() {
    return wrappee.getPatientId();
  }

  @Override
  public String getCondition() {
    return wrappee.getCondition();
  }

  @Override
  public long getTimestamp() {
    return wrappee.getTimestamp();
  }
}
