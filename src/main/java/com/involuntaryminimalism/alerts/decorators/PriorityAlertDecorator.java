package com.involuntaryminimalism.alerts.decorators;

import com.involuntaryminimalism.alerts.Alert;

public class PriorityAlertDecorator extends AlertDecorator {
  public PriorityAlertDecorator(Alert wrappee) {
    super(wrappee);
  }
}
