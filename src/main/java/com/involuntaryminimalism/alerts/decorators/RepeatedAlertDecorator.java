package com.involuntaryminimalism.alerts.decorators;

import com.involuntaryminimalism.alerts.Alert;
import java.util.concurrent.Callable;

public class RepeatedAlertDecorator extends AlertDecorator {
  private final Callable<Boolean> repeatCondition;

  public RepeatedAlertDecorator(Alert wrappee, Callable<Boolean> repeatCondition) {
    super(wrappee);
    this.repeatCondition = repeatCondition;
  }

  @Override
  public String getCondition() {
    String extraMessage = shouldRepeat() ? "Repeat me please! " : "";
    return extraMessage + super.getCondition();
  }

  /**
   * Decides whether the alert should be repeated
   *
   * @return true if the repeatCondition is evaluated true, false if the condition is false,
   *     failing, or missing
   */
  private boolean shouldRepeat() {
    if (repeatCondition == null) {
      return false;
    }
    boolean out;
    try {
      out = repeatCondition.call();
    } catch (Exception e) {
      // exception during check is supressed
      return false;
    }
    return out;
  }
}
