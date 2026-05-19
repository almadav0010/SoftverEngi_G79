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
import com.involuntaryminimalism.alerts.types.ECGAlert;

public class ECGAlertTest {
    @Test
    void OxygenLevel(){
      var instance = DataStorage.getInstance();
      var alertManager = new AlertManager();
      var doctor = new TesterDoctor();
      AlertManager.registerListener(doctor);
      var patient = new Patient(14);
      instance.addPatientData(14, 10, Label.ECG, 100);
      instance.addPatientData(14, 20, Label.ECG, 100);
      instance.addPatientData(14, 20, Label.ECG, 100);
      instance.addPatientData(14, 20, Label.ECG, 100);
      instance.addPatientData(14, 20, Label.ECG, 100);
      instance.addPatientData(14, 4000, Label.ECG, 100);
      var alertGenerator = new AlertGenerator();
      alertGenerator.evaluateData(patient);
      assertEquals(ECGAlert.class.getName(), doctor.lastAlert.getClass().getName());

    }
}
