package com.involuntaryminimalism.alerts;

import com.involuntaryminimalism.alerts.strategies.BloodPressureCriticalStrategy;
import com.involuntaryminimalism.alerts.strategies.BloodPressureTrendStrategy;
import com.involuntaryminimalism.alerts.types.BloodPressureCriticalAlertFactory;
import com.involuntaryminimalism.alerts.types.BloodPressureTrendAlertFactory;
import com.involuntaryminimalism.data_management.DataStorage;
import com.involuntaryminimalism.data_management.Patient;

import java.util.Optional;

import com.involuntaryminimalism.alerts.strategies.BloodOxygenStrategy;
import com.involuntaryminimalism.alerts.types.BloodOxygenAlert;
import com.involuntaryminimalism.alerts.types.BloodOxygenAlertFactory;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data and generating alerts
 * when certain predefined conditions are met. This class relies on a {@link DataStorage} instance
 * to access patient data and evaluate it against specific health criteria. It also relies on a
 * {@link AlertManager} instance that distributes the triggered alerts, and further process them.
 */
public class AlertGenerator {
  private final BloodPressureCriticalStrategy stratBloodPressureCritical =
      new BloodPressureCriticalStrategy();
  private final BloodPressureTrendStrategy stratBloodPressureTrend =
      new BloodPressureTrendStrategy();
  private final BloodOxygenStrategy stratOxygenTrend =
      new BloodOxygenStrategy();

  /**
   * Evaluates the specified patient's data to determine if any alert conditions are met. If a
   * condition is met, an alert is triggered via the {@link #triggerAlert} method. This method
   * should define the specific conditions under which an alert will be triggered.
   *
   * @param patient the patient data to evaluate for alert conditions
   */
  public void evaluateData(Patient patient) {
    // get all records -> minmax of long as time windows
    var allRecords =
        DataStorage.getInstance()
            .getRecords(patient.getPatientId(), Long.MIN_VALUE, Long.MAX_VALUE);
    // TODO: implement sliding window here so instead of all record, a timestamp is kept
    //       for all patients marking how far their records had been processed, and then
    //       continue one record at a time.

    Optional<String> result;
    for (var record : allRecords) {
      // 1-2. blood pressure data alerts (both types)
      result = stratBloodPressureCritical.checkAlert(record);
      if (result.isPresent()) {
        triggerAlert(
            new BloodPressureCriticalAlertFactory()
                .createAlert(
                    String.valueOf(record.getPatientId()), result.get(), record.getTimestamp()));
      }
      result = stratBloodPressureTrend.checkAlert(record);
      if (result.isPresent()) {
        triggerAlert(
            new BloodPressureTrendAlertFactory()
                .createAlert(
                    String.valueOf(record.getPatientId()), result.get(), record.getTimestamp()));
      }

      // 3. SpO2 low
      result = stratOxygenTrend.checkAlert(record);
      if (result.isPresent()) {
        triggerAlert(
            new BloodOxygenAlertFactory()
                .createAlert(
                    String.valueOf(record.getPatientId()), result.get(), record.getTimestamp()));
      }
      // 4. rapid drop
      // 5. hypotensive hypoexemia,
      // 6. ECG
      // 7. triggered alert
    }
  }

  /**
   * Triggers an alert for the monitoring system. This method can be extended to notify medical
   * staff, log the alert, or perform other actions. The method currently assumes that the alert
   * information is fully formed when passed as an argument.
   *
   * @param alert the alert object containing details about the alert condition
   */
  private void triggerAlert(Alert alert) {
    // Implementation might involve logging the alert or notifying staff
    AlertManager.triggerAlert(alert);
  }
}
