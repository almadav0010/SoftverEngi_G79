package com.involuntaryminimalism.alerts.strategies;

import com.involuntaryminimalism.alerts.strategies.AlertStrategy;
import com.involuntaryminimalism.data_management.PatientRecord;

import java.util.Optional;
import java.util.ArrayList;

import com.involuntaryminimalism.Label;


public class BloodOxygenDropStrategy implements AlertStrategy {
  private final ArrayList<PatientRecord> previousAlerts = new ArrayList<>();
  @Override
  public Optional<String> checkAlert(PatientRecord record) {
    Label recordType = record.getRecordType();

    // skip irrelevant measurements
    if (recordType != Label.Saturation) {
      return Optional.empty();
    }
    updateList(record);
    for (PatientRecord pr : previousAlerts) {
      if(pr.getMeasurementValue()-record.getMeasurementValue() > 5){
        // we dropped by 5% compared to any measurementin the last 10 mins
        return Optional.of("Patient's Blood oxygen level dropped by more than 5% in the last 10 minutes!"+
         "It dropped by: "+(pr.getMeasurementValue()-record.getMeasurementValue()));
      }
    }


    return Optional.empty();
  }
  private void updateList(PatientRecord record){
    for (PatientRecord pr : previousAlerts) {
      if(record.getTimestamp()-pr.getTimestamp()>600) // removes everything more than 10 mins ago
      {
        previousAlerts.remove(pr);
      }
    }
    previousAlerts.add(record); //adds the new record
  }
}
