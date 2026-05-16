package alerts;

import org.junit.jupiter.api.Test;

import com.involuntaryminimalism.TesterDoctor;
import com.involuntaryminimalism.alerts.AlertManager;
import com.involuntaryminimalism.alerts.AlertGenerator;
import com.involuntaryminimalism.Label;
import com.involuntaryminimalism.alerts.types.BloodPressureCriticalAlert;
import com.involuntaryminimalism.data_management.DataStorage;
import com.involuntaryminimalism.data_management.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.involuntaryminimalism.alerts.types.BloodOxygenAlert;
import com.involuntaryminimalism.alerts.types.HypotensiveHypoxemiaAlert;

public class HyHyAlertTest {
    @Test
    void OxygenLevel(){
      var instance = DataStorage.getInstance();
      var alertManager = new AlertManager();
      var doctor = new TesterDoctor();
      AlertManager.registerListener(doctor);
      var patient = new Patient(11);
      instance.addPatientData(11, 90.5, Label.Saturation, 100);
      instance.addPatientData(11, 86.5, Label.SystolicPressure, 100);
      var alertGenerator = new AlertGenerator();
      alertGenerator.evaluateData(patient);
      assertEquals(HypotensiveHypoxemiaAlert.class.getName(), doctor.lastAlert.getClass().getName());

    }
}
