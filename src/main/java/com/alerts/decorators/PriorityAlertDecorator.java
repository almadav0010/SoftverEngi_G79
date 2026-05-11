package com.alerts.decorators;

import com.alerts.Alert;

public class PriorityAlertDecorator extends AlertDecorator {
  public PriorityAlertDecorator(Alert wrappee) {
    super(wrappee);
  }
}
