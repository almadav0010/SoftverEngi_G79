package com.involuntaryminimalism;

import com.involuntaryminimalism.alerts.Alert;
import com.involuntaryminimalism.alerts.AlertListener;

public class TesterDoctor implements AlertListener {
  public int numberOfAlerts = 0;
  public Alert lastAlert = null;
  public void onAlert(Alert alert) {
    // dummy alert handling of doctor
    lastAlert = alert;
    System.out.println("Alert number " + ++numberOfAlerts + ": " + alert.toString());
  }
}
