package alerts;

import static org.junit.jupiter.api.Assertions.*;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.TesterDoctor;
import com.involuntaryminimalism.alerts.AlertGenerator;
import com.involuntaryminimalism.alerts.AlertManager;
import com.involuntaryminimalism.alerts.types.BloodPressureTrendAlert;
import com.involuntaryminimalism.data_management.DataStorage;
import com.involuntaryminimalism.data_management.Patient;
import org.junit.jupiter.api.Test;

public class BloodPressureTrendAlertTests {
  @Test
  void systolicCritical() {
    var instance = DataStorage.getInstance();
    var doctor = new TesterDoctor();
    AlertManager.registerListener(doctor);
    var patientZero = new Patient(2);
    instance.addPatientData(2, 100, Label.SystolicPressure, 100);
    instance.addPatientData(2, 120, Label.SystolicPressure, 200);
    instance.addPatientData(2, 140, Label.SystolicPressure, 300);
    var alertGenerator = new AlertGenerator();
    alertGenerator.evaluateData(patientZero);
    assertEquals(BloodPressureTrendAlert.class.getName(), doctor.lastAlert.getClass().getName());
  }

  @Test
  void diasteticCritical() {
    var instance = DataStorage.getInstance();
    var doctor = new TesterDoctor();
    AlertManager.registerListener(doctor);
    var patientZero = new Patient(3);
    instance.addPatientData(3, 70, Label.DiastolicPressure, 100);
    instance.addPatientData(3, 90, Label.DiastolicPressure, 200);
    instance.addPatientData(3, 110, Label.DiastolicPressure, 300);
    var alertGenerator = new AlertGenerator();
    alertGenerator.evaluateData(patientZero);
    assertEquals(BloodPressureTrendAlert.class.getName(), doctor.lastAlert.getClass().getName());
  }

}
