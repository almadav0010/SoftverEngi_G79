package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.TesterDoctor;
import com.involuntaryminimalism.alerts.AlertGenerator;
import com.involuntaryminimalism.alerts.AlertManager;
import com.involuntaryminimalism.alerts.types.BloodOxygenAlert;
import com.involuntaryminimalism.data_management.DataStorage;
import com.involuntaryminimalism.data_management.Patient;
import org.junit.jupiter.api.Test;

public class BloodOxygenSaturationAlertTest {
  @Test
  void OxygenLevel() {
    var instance = DataStorage.getInstance();
    var alertManager = new AlertManager();
    var doctor = new TesterDoctor();
    AlertManager.registerListener(doctor);
    var patient = new Patient(5);
    instance.addPatientData(5, 90.5, Label.Saturation, 100);
    var alertGenerator = new AlertGenerator();
    alertGenerator.evaluateData(patient);
    assertEquals(BloodOxygenAlert.class.getName(), doctor.lastAlert.getClass().getName());
  }
}
