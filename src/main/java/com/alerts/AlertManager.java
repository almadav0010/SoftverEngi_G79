package com.alerts;

import com.Doctor;

import java.util.List;

public class AlertManager {
  List<AlertListener> doctorsToNotice = List.of();

  public void registerDoctor(Doctor doctor) {
    if (!doctorsToNotice.contains(doctor)) {
      doctorsToNotice.add(doctor);
    }
  }

  public void deRegisterDoctor(Doctor doctor) {
    if (doctorsToNotice.contains(doctor)) {
      doctorsToNotice.remove(doctor);
    }
  }
}
