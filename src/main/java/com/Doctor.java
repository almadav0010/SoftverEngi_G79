package com;

import com.alerts.Alert;
import com.alerts.AlertListener;

public class Doctor implements AlertListener {
  public void onAlert(Alert alert) {
    // dummy alert handling of doctor
    System.out.println(alert.toString());
  }
}
