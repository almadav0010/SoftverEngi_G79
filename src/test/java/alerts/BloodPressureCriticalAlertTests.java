package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.TesterDoctor;
import com.involuntaryminimalism.alerts.AlertGenerator;
import com.involuntaryminimalism.alerts.AlertManager;
import com.involuntaryminimalism.alerts.types.BloodPressureCriticalAlert;
import com.involuntaryminimalism.data_management.DataStorage;
import com.involuntaryminimalism.data_management.Patient;
import org.junit.jupiter.api.Test;

public class BloodPressureCriticalAlertTests {
  @Test
  void systolicCritical() {
    var instance = DataStorage.getInstance();
    var alertManager = new AlertManager();
    var doctor = new TesterDoctor();
    AlertManager.registerListener(doctor);
    var patient = new Patient(0);
    instance.addPatientData(0, 300, Label.SystolicPressure, 100);
    var alertGenerator = new AlertGenerator();
    alertGenerator.evaluateData(patient);
    assertEquals(BloodPressureCriticalAlert.class.getName(), doctor.lastAlert.getClass().getName());
  }

  @Test
  void diasteticCritical() {
    var instance = DataStorage.getInstance();
    var doctor = new TesterDoctor();
    AlertManager.registerListener(doctor);
    var patient = new Patient(1);
    instance.addPatientData(1, 300, Label.DiastolicPressure, 100);
    var alertGenerator = new AlertGenerator();
    alertGenerator.evaluateData(patient);
    assertEquals(BloodPressureCriticalAlert.class.getName(), doctor.lastAlert.getClass().getName());
  }
}
