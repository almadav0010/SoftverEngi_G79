package com.involuntaryminimalism.alerts;

import java.util.ArrayList;
import java.util.List;

public final class AlertManager {
  private static final List<AlertListener> listeners = new ArrayList<>();

  public static void registerListener(AlertListener listener) {
    if (!listeners.contains(listener)) {
      listeners.add(listener);
    }
  }

  public static void deRegisterListener(AlertListener listener) {
    listeners.remove(listener);
  }

  public static void triggerAlert(Alert alert) {
    for (var listener : listeners) {
      listener.onAlert(alert);
    }
  }
}
