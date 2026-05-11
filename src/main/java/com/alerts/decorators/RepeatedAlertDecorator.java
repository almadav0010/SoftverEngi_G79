package com.alerts.decorators;

import com.alerts.Alert;

public class RepeatedAlertDecorator extends AlertDecorator {
  public RepeatedAlertDecorator(Alert wrappee) {
    super(wrappee);
  }
}
