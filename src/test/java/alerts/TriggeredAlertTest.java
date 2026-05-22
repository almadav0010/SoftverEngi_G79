package alerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.TesterDoctor;
import com.involuntaryminimalism.alerts.AlertGenerator;
import com.involuntaryminimalism.alerts.AlertManager;
import com.involuntaryminimalism.alerts.types.TriggeredAlert;
import com.involuntaryminimalism.data_management.DataStorage;
import com.involuntaryminimalism.data_management.Patient;
import org.junit.jupiter.api.Test;

public class TriggeredAlertTest {
  @Test
  void OxygenLevel() {
    var instance = DataStorage.getInstance();
    var alertManager = new AlertManager();
    var doctor = new TesterDoctor();
    AlertManager.registerListener(doctor);
    var patient = new Patient(16);
    instance.addPatientData(16, 1, Label.Alert, 100);
    var alertGenerator = new AlertGenerator();
    alertGenerator.evaluateData(patient);
    assertEquals(TriggeredAlert.class.getName(), doctor.lastAlert.getClass().getName());
  }
}
