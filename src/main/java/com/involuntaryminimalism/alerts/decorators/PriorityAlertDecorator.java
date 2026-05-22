package com.involuntaryminimalism.alerts.decorators;

import com.involuntaryminimalism.alerts.Alert;

public class PriorityAlertDecorator extends AlertDecorator {
  private final int priority;
  public PriorityAlertDecorator(Alert wrappee, int priority) {
    super(wrappee);
    if (priority < 0) {
      throw new IllegalArgumentException("Alert priority must be non negative, but " + priority + " was given!");
    }
    this.priority = priority;
  }

  @Override
  public String getCondition() {
    return "[PRIORITY: " + priority + "]: " + super.getCondition();
  }
}
