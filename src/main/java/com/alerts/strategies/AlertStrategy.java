package com.alerts.strategies;

import com.data_management.Patient;

import java.util.Optional;

public interface AlertStrategy {
  /**
   * An AlertStrategy must keep track how far into time a patient's measurements had
   * been processed, this method enforces it.
   */
  long lastProcessedTimeStamp();
  /**
   * Checks if a specific alert type's conditions are met and format it into an
   * {Alert} if so.
   * @param patient the patient object whose records are used to check if an alert condition is met
   * @return optional condition for the alert, if the conditions are not met it is empty
   * **/
  Optional<String> checkAlert(Patient patient);
}
