package com.alerts;

import com.Label;
import com.alerts.strategies.BloodPressureStrategy;
import com.alerts.types.BloodPressureAlertFactory;
import com.cardio_generator.outputs.ConsoleOutputStrategy;
import com.data_management.DataStorage;
import com.data_management.Patient;
import java.util.Optional;

/**
 * The {@code AlertGenerator} class is responsible for monitoring patient data and generating alerts
 * when certain predefined conditions are met. This class relies on a {@link DataStorage} instance
 * to access patient data and evaluate it against specific health criteria.
 */
public class AlertGenerator {
  private final DataStorage dataStorage;
  private final BloodPressureStrategy stratBloodPressure = new BloodPressureStrategy();

  /**
   * Constructs an {@code AlertGenerator} with a specified {@code DataStorage}. The {@code
   * DataStorage} is used to retrieve patient data that this class will monitor and evaluate.
   *
   * @param dataStorage the data storage system that provides access to patient data
   */
  public AlertGenerator(DataStorage dataStorage) {
    this.dataStorage = dataStorage;
  }

  /**
   * Evaluates the specified patient's data to determine if any alert conditions are met. If a
   * condition is met, an alert is triggered via the {@link #triggerAlert} method. This method
   * should define the specific conditions under which an alert will be triggered.
   *
   * @param patient the patient data to evaluate for alert conditions
   */
  public void evaluateData(Patient patient) {
    // get all records -> minmax of long as time windows
    var allRecords = patient.getRecords(Long.MIN_VALUE, Long.MAX_VALUE);

    Optional<String> result;
    for (var record : allRecords) {
      // 1. blood pressure data alerts
      result = stratBloodPressure.checkAlert(record);
      if (result.isPresent()) {
        triggerAlert(
            new BloodPressureAlertFactory()
                .createAlert(
                    String.valueOf(record.getPatientId()), result.get(), record.getTimestamp()));
      }

      // 2. blood saturation data alerts
      // 3. Combined Alert: Hypotensive Hypoxemia Alert
      // 4. ECG Data Alerts
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
    new ConsoleOutputStrategy()
        .output(
            Integer.valueOf(alert.getPatientId()),
            alert.getTimestamp(),
            Label.Alert.name(),
            alert.getCondition());
  }
}
