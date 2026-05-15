package com.involuntaryminimalism.alerts.decorators;

import com.involuntaryminimalism.alerts.Alert;

public class RepeatedAlertDecorator extends AlertDecorator {
  public RepeatedAlertDecorator(Alert wrappee) {
    super(wrappee);
  }
}
