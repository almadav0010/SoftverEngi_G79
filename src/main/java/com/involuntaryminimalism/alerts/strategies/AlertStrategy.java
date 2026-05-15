package com.involuntaryminimalism.alerts.strategies;

import com.involuntaryminimalism.data_management.PatientRecord;
import java.util.Optional;

public interface AlertStrategy {
  /**
   * Checks if a specific alert type's conditions are met and format it into an {Alert} if so.
   *
   * @param record to be checked if it requires an alert to be formed
   * @return optional condition for the alert, if the conditions are not met it is empty *
   */
  Optional<String> checkAlert(PatientRecord record);
}
